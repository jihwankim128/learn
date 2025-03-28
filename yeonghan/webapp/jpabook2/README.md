## 🔥 실전! 스프링 부트와 JPA 활용2 - API 개발과 성능 최적화

---

### 🏁 해당 강의를 시작하는 이유 ?

* 실전! 스프링 부트와 JPA 활용1 편을 수강하면서 이어서 진행되는 실습 강의라서 선택했습니다.
  * 우선 1편에서 얻은 내용이 너무 많았기 때문에, 놓치고 싶지 않았습니다.
  * 1편은 SSR(Server-Side-Rendering)방식의 개발 실습을 진행했습니다.
  * 2편은 API 위주 방식의 개발 실습을 진행하게 됩니다. 
  * 트렌드는 주로 API를 이용해 통신하므로 실무에서는 어떻게 API를 활용하는 것이 좋을지 알 수 있을 것 같습니다.
* 스스로 느끼기에 1편은 프로젝트의 MVP(Minimum Viable Product) 제작에 초점이 맞춰진 느낌입니다.
  * 2편은 MVP로 작성된 서비스를 JPA의 성능을 최적화 할 수 있는 방법을 학습할 수 있다고 합니다.
  * 1편에서 언급된 적 있던 N+1 문제, N:M 매핑의 단점 등을 보완하는 방법들을 학습할 수 있을 것으로 예상됩니다.

### 🔎 해당 강의를 수강한 뒤 얻을 수 있는 결과물 ?
    
* 기존에 알고 있던 API 개발의 부족한 개념이 있었다면, 보완할 수 있을 것으로 예상됩니다.
* 실습 프로젝트로 성능 문제가 발생할 수 있는 케이스들을 경험해 볼 수 있을 것으로 예상됩니다.
* 현재 진행중인 프로젝트에서 개선할 부분을 찾을 수 있을 것으로 예상됩니다.

### 📝 학습 후 얻게된 내용

- 1편 강의에 대해 정리한 내용 중 fetch 전략에 대해 스스로 학습하면서 스스로 깨닫게 된 내용 중 N+1 문제가 있었습니다. 하지만, N+1이 무조건 나쁜 것은 아니라는 것을 이번 강의를 통해 알게됐습니다.
  - Lazy 강제 초기화를 통해 JPA에게 책임을 부여하는 방법은 N+1 문제가 발생할 수 있지만, 데이터량에 따라 성능 차이가 적고 오히려 개발 속도를 증가시킬 수 있는 장점도 있기 때문입니다.
  - 1편 정리 링크 - ([README](https://github.com/jihwankim128/yeonghan/tree/main/webapp/jpabook), [블로그 정리](https://velog.io/@jihwankim128/AI-Carrer-School-12%EC%A3%BC-%EC%B0%A8-%ED%95%99%EC%8A%B5-%EA%B2%B0%EA%B3%BC))
- 조회 작업 시 JPA에서 Entity로 조회한 뒤 DTO로 변환하는 방식과 JPA에서 직접 DTO로 매핑하는 방식의 장단점을 알게됐습니다.
  - JPA To Entity
    - 장점: JPA의 추상황 기술을 최대한 활용해서 비교적 간단하게 쿼리 작업을 할 수 있습니다.
    - 단점: DB는 외부 시스템이므로 접근하려면 Network를 타야하고 실제 Data는 row level에 존재하므로 데이터 이동 간 필요 없는 필드는 추가 비용이 될 수 있습니다.
  - JPA To DTO
    - 장점: Client 측에서 필요로 하는 최소한의 필드 정보로 DB와 통신 간 Network 비용이 최적 상태가 됩니다.
    - 단점: JPA의 추상화 기술을 최대한 활용할 수 없습니다. 또, Client 측에 빈번한 요구사항에 따라 마찬가지로 빈번한 수정이 발생할 수 있습니다.
  - 상황 별 선택
    - MSA를 도입 했을 때, 각 모듈 간 API 통신으로 특정 도메인의 전체 스펙이 필요할 경우 JPA To Entity 방식을 사용하면 좋겠다는 생각이 들었습니다.
    - 게시글 삭제 시, 관련된 댓글들을 모두 Soft Delete 하는 방식과 같이 Service Layer에서 의존된 도메인 간 추가작업이 발생할 때, JPA To Entity 좋겠다는 생각이 들었습니다.
    - UI에 맞는 API 스펙이 필요할 때는 JPA To DTO 방식이 좋겠습니다. Query와 Command를 분리하는 장점을 챙기면서 UI가 바뀌더라도 Query가 관련된 클래스만 수정하면 되는 장점이 있습니다.
 - hibernate.default_batch_fetch_size 옵션에 대해서 명확히 이해했습니다.
   - 기존에는 관례처럼 그냥 사용하던 습관이 있었는데 이를 통해서 fetch를 위한 JPQL을 따로 작성하지 않아도 JPA 추상화 기술을 통해 N+1 문제를 완화 할 수 있다는 것을 알게됐습니다.
 - 도메인 간 연관관계가 1:N 일 때, N에서 한번 더 1:N의 연관관계가 있다면 쿼리를 분리해야 된다.
   - 1:N 관계는 N관계에서 1에 대한 식별 정보를 알고 있기 때문에 join 절에서 예상하는 query 수가 나온다.
   - 후자의 상황에서는 1:N:M 상황이므로 M이 1에 대한 식별 정보가 없다면 1에 대한 query * M에 대한 query 만큼 추가 query가 생성된다.
 - OSIV(Open Session In View)를 활용해야한다.
   - Transaction Isolation이 Service + Repository Layer 뿐만 아니라 Controller Layer까지 영향을 미친다.
   - OSIV를 활성화 한다면, 트랜잭션 범위 때문에 DB Connection을 더 광범위하게 가져간다. 트래픽이 많아질수록 DB 반환 속도가 느려질 수 있는 것이다.
   - OSIV를 비활성화 한다면, Controller Layer와 Service + Repository Layer 에 대한 책임을 완전히 분리할 수 있다.
   - 만약 OSIV를 계속해서 활성화하고 작업하다가 팀원 중 Controller Layer와 Service Layer의 경계를 명확히 구분하지 못하고 Controller에서 Lazy Loading을 활용하고 있다고 생각해보자.
     - 추후 트래픽이 많아져서 DB Connection이 늦게 반환되는 것을 깨닫고 OSIV를 비활성화하지만, 코드를 수정해야 할 내용 또한 많아질 것이다.
     - 프로젝트 시작부터 OSIV를 끄는 것은 좋은 습관일 것으로 예상된다.
### 소감

- 이번 강의는 실제 서비스에서 가장 많은 트래픽이 발생하는 조회에 대한 최적화 기술이 많이 담겨 있습니다. 강의를 통해 서비스를 개발할 때, MVP 개발 상황, 추후 서비스 응답 속도 향상을 위한 리팩토링 등에서 어떤 방향으로 JPA 기술을 적용해야 될 지 깨닫게 되는 시간이었습니다.

### 학습 환경

> 강의와 달리 현재 많이 사용하는 기술로 학습을 진행했습니다.

- Gradle 8.11.1
- Spring Boot 3.4.1
- Java 21
- H2 DB

### 강의 정보

- 인프런 학습 링크 : https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-API%EA%B0%9C%EB%B0%9C-%EC%84%B1%EB%8A%A5%EC%B5%9C%EC%A0%81%ED%99%94

### 학습 정리

- Velog : https://velog.io/@jihwankim128/AI-Carrer-School-%ED%95%99%EC%8A%B5-%EA%B2%B0%EA%B3%BC2

package org.study.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.study.auth.domain.UserAuth;
import org.study.common.repository.entity.TimeBaseEntity;

@Entity
@Table(name = "community_user_auth")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class UserAuthEntity extends TimeBaseEntity {

    @Id
    private String email;
    private String password;
    private String role;
    private Long userId;
    private LocalDateTime lastLoginDt;

    public UserAuthEntity(UserAuth userAuth, Long userId) {
        this.email = userAuth.getEmail();
        this.password = userAuth.getPassword();
        this.role = userAuth.getUserRole();
        this.userId = userId;
    }

    public UserAuth toUserAuth() {
        return new UserAuth(email, password, role, userId);
    }

    public void updateLastLoginAt() {
        lastLoginDt = LocalDateTime.now();
    }

}

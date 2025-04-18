package org.demo.jpashop.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.demo.jpashop.domain.Delivery;
import org.demo.jpashop.domain.Member;
import org.demo.jpashop.domain.Order;
import org.demo.jpashop.domain.OrderItem;
import org.demo.jpashop.domain.item.Item;
import org.demo.jpashop.repository.ItemRepository;
import org.demo.jpashop.repository.MemberRepository;
import org.demo.jpashop.repository.OrderRepository;
import org.demo.jpashop.repository.OrderSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    // 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    // 검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}

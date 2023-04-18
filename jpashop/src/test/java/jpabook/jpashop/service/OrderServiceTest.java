package jpabook.jpashop.service;


import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.itemtype.Book;
import jpabook.jpashop.domain.type.OrderStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    EntityManager entityManager;

    @Autowired
    OrderRepository orderRepository;


    @Test
    public void ProductOrderTest() {

        //given
        Member member = createMember();
        Book book = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER, getOrder.getStatus(),"상품 주문시 상태는 ORDER");
        assertEquals(1,getOrder.getOrderItems().size(),"주문한 상품 종류 수 정확");
        assertEquals(10000 * orderCount,getOrder.getTotalPrice(),"주문 가격은 가격 * 수량이다");
        assertEquals(8,book.getStockQuantity(),"주문수량만큼 재고가 줄어야한다.");
    }

    @Test
    public void productOrderExceedsStockQuantityTest() {
        //given
        Member member = createMember();
        Book book = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;

        //when then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(),book.getId(),orderCount);
        });
    }
    @Test
    public void withdrawOrderTest() {
        //given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);
        int orderCount = 7;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //when
        Order findOrder = orderRepository.findOne(orderId);
        assertEquals(book.getStockQuantity(),3);
        orderService.cancelOrder(orderId);

        //then
        assertEquals(book.getStockQuantity(),10);
        assertEquals(findOrder.getStatus(),OrderStatus.CANCEL);
    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        entityManager.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = Member.builder()
                .name("JM")
                .address(new Address("서울","강가","123-123"))
                .build();
        entityManager.persist(member);
        return member;
    }




}
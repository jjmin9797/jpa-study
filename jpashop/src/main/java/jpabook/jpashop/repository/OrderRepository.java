package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long orderId) {
        return em.find(Order.class, orderId);
    }




    public List<Order> findByMemberName(String memberName, OrderStatus type) {
        return em.createQuery("select o " +
                        "from Order " +
                        "o join fetch o.member " +
                        "where o.member.name = :memberName " +
                        "and o.status = :type", Order.class)
                .setParameter("memberName",memberName)
                .setParameter("type",type)
                .getResultList();
    }
}

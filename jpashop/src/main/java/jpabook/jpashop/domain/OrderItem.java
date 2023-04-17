package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        item.removeStock(count);
        return OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .count(count)
                .build();
    }

    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return count * orderPrice;
    }
}

package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    /**
     * 재고 증가
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStick = this.stockQuantity - quantity;
        if (restStick < 0 ) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStick;
    }
}

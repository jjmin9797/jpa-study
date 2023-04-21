package jpabook.jpashop.domain.itemtype;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpabook.jpashop.domain.Item;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Book")
@Builder
public class Book extends Item {

    private String author;
    private String isbn;
}

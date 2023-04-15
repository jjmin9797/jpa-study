package jpabook.jpashop.domain.itemtype;

import jakarta.persistence.Entity;
import jpabook.jpashop.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album extends Item {
    private String artist;
    private String etc;
}

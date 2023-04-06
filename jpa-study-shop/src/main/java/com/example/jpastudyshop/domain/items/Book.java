package com.example.jpastudyshop.domain.items;

import com.example.jpastudyshop.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Book extends Item {

    private String author;
    private String isbn;
}

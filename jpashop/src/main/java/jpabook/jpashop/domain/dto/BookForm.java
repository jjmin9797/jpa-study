package jpabook.jpashop.domain.dto;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.itemtype.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
public class BookForm {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public static Book toEntity(BookForm form) {
        Book book = new Book();
        book.setPrice(form.getPrice());
        book.setName(form.getName());
        book.setId(form.getId());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        return book;
    }

    public static BookForm toForm(Book book) {
        return BookForm.builder()
                .id(book.getId())
                .name(book.getName())
                .price(book.getPrice())
                .stockQuantity(book.getStockQuantity())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .build();
    }
}

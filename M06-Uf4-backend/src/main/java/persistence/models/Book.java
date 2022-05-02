package persistence.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Book {

    private Integer book_id ;
    private String title;
    private String isbn;
    private int author_id;
    private int category_id;
    private String publication_date;
    private String edition;
    private String pages;
    private int quantity;
    private double price;


}

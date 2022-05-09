package persistence.models;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

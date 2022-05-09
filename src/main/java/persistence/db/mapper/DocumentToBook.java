package persistence.db.mapper;

import org.bson.Document;
import persistence.models.Book;

public class DocumentToBook implements CustomMapper<Document, Book>{
    @Override
    public Book map(Document document) {
        return Book.builder()
                .book_id(document.getInteger("id"))
                .title(document.getString("title"))
                .isbn(document.getString("isbn"))
                .author_id(document.getInteger("author_id"))
                .category_id(document.getInteger("category_id"))
                .publication_date(document.getString("publication_date"))
                .edition(document.getString("edition"))
                .quantity(document.getInteger("quantity"))
//                .price(document.getDouble("quantity"))
                .build();
    }
}

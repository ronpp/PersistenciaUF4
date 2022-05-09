package persistence.db.mapper;

import persistence.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToBook implements CustomMapper<ResultSet, Book> {
    @Override
    public Book map(ResultSet rs) {
        Book book = new Book();
        try {
                book = Book.builder()
                        .book_id(rs.getInt("book_id"))
                        .title(rs.getString("title"))
                        .isbn(rs.getString("isbn"))
                        .author_id(rs.getInt("author_id"))
                        .category_id(rs.getInt("category_id"))
                        .publication_date(rs.getString("publication_date"))
                        .edition(rs.getString("edition"))
                        .pages(rs.getString("pages"))
                        .quantity(rs.getInt("quantity"))
                        .price(rs.getDouble("price"))
                        .build();

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return book;
    }
}

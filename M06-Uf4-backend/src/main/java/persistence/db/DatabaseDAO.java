package persistence.db;

import persistence.models.Book;

import java.util.Optional;
import java.util.stream.Stream;

public interface DatabaseDAO {

    Stream<Book> listAllBooks();
    Optional<Book> findBookById(int id);
    Optional<Book> findBookByIsbn(String isbn);
    Stream<Book> addBook(Book book);
    void updateBook(int id);
    void deleteBook(int id);



}

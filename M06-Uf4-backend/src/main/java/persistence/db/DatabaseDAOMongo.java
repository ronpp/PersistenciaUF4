package persistence.db;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import persistence.db.datasource.MongoDataSource;
import persistence.db.mapper.DocumentToBook;
import persistence.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseDAOMongo implements DatabaseDAO {


    private final DocumentToBook mapper = new DocumentToBook();



    @Override
    public Stream<Book> listAllBooks() {
        MongoCollection<Document> books = MongoDataSource.getCollection("books");
        List<Book> bookList =  new ArrayList<>();
        books.find().forEach(book -> {
            bookList.add(mapper.map(book));
        });
        return bookList.stream();
    }

    @Override
    public Optional<Book> findBookById(int id) {
        MongoCollection<Document> books = MongoDataSource.getCollection("books");
        List<Book> bookList =  new ArrayList<>();
        books.find(eq("id",id)).forEach(book -> {
            bookList.add(mapper.map(book));
        });
        return bookList.stream().findAny();
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        MongoCollection<Document> books = MongoDataSource.getCollection("books");
        List<Book> bookList =  new ArrayList<>();
        books.find(eq("id",isbn)).forEach(book -> {
            bookList.add(mapper.map(book));
        });
        return bookList.stream().findAny();
    }

    @Override
    public Stream<Book> addBook(Book book) {
        MongoCollection<Document> books = MongoDataSource.getCollection("books");
        Document document = new Document();
        document.append("title", book.getTitle())
                .append("isbn", book.getIsbn())
                .append("author_id",book.getAuthor_id())
                .append("category_id",book.getCategory_id())
                .append("publication_date",book.getPublication_date())
                .append("edition",book.getEdition())
                .append("pages",book.getPages())
                .append("quantity",book.getQuantity())
                .append("price",book.getPrice());
        books.insertOne(document);
        return Stream.of(mapper.map(books.find(eq("title", book.getTitle())).first()));
    }

    @Override
    public void updateBook(int id) {
        // TODO: Pending to finish
    }

    @Override
    public void deleteBook(int id) {
        MongoCollection<Document> books = MongoDataSource.getCollection("books");
        books.deleteOne(eq("id", id));

    }
}

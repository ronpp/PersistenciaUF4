package persistence.db;

import persistence.db.datasource.MySqlDataSource;
import persistence.db.mapper.ResultSetToBook;
import persistence.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DatabaseDAOMysql implements DatabaseDAO{

    private final ResultSetToBook mapper = new ResultSetToBook();

    @Override
    public Stream<Book> listAllBooks() {
        String SQL_QUERY = "SELECT * FROM books";
        List<Book> bookList =  new ArrayList<>();
        try(Connection conn = MySqlDataSource.getConnection();
            PreparedStatement pst = conn.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery()) {
            while (rs.next()){
                bookList.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList.stream();
    }

    @Override
    public Optional<Book> findBookById(int id) {
        String SQL_QUERY = "SELECT * FROM books WHERE book_id = ?";
        Book book = null;
        try(Connection conn = MySqlDataSource.getConnection();
            PreparedStatement pst = conn.prepareStatement(SQL_QUERY)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                book = mapper.map(rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(book);
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        String SQL_QUERY = "SELECT * FROM books WHERE isbn = ?";
        Book book = null;
        try(Connection conn = MySqlDataSource.getConnection();
            PreparedStatement pst = conn.prepareStatement(SQL_QUERY)) {

            pst.setString(1, isbn);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                book = mapper.map(rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(book);
    }

    @Override
    public Stream<Book> addBook(Book book) {
        String SQL_QUERY = "INSERT INTO books(title, isbn, author_id, category_id, publication_date, edition, pages, quantity, price)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int row = 0;
        try(Connection conn = MySqlDataSource.getConnection();
            PreparedStatement pst = conn.prepareStatement(SQL_QUERY)) {

            pst.setString(1, book.getTitle());
            pst.setString(2, book.getIsbn());
            pst.setInt(3, book.getAuthor_id());
            pst.setInt(4, book.getCategory_id());
            pst.setString(5, book.getPublication_date());
            pst.setString(6, book.getEdition());
            pst.setString(7, book.getPages());
            pst.setInt(8, book.getQuantity());
            pst.setDouble(9, book.getPrice());
            row  = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
       if(row != 0){
           return Stream.of(findBookByIsbn(book.getIsbn()).get());
       }
        return null;
    }


    @Override
    public void updateBook(int id) {
        // TODO: Pending to finish

    }

    @Override
    public void deleteBook(int id) {
        String SQL_QUERY = "DELETE FROM books WHERE book_id = ?";
        int row  = 0;
        try (Connection conn = MySqlDataSource.getConnection();
             PreparedStatement pst = conn.prepareStatement(SQL_QUERY)){
            pst.setInt(1, id);
            row = pst.executeUpdate();
            System.out.println("deleted rows: " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

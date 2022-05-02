package persistence.db;

import persistence.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseDAOMysql implements DatabaseDAO{


    @Override
    public Stream<Book> listAllBooks() {
        String SQL_QUERY = "SELECT * FROM books";
        List<Book> bookList =  new ArrayList<>();;
        try(Connection conn = MySqlDataSource.getConnection();
            PreparedStatement pst = conn.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery()) {
            while (rs.next()){
                Book book = Book.builder()
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
                bookList.add(book);
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
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(book);
    }

    @Override
    public Stream<Book> addBook(Book book) {
        return null;
    }


    public static  Map<String, String> testUpdate(){
        String sql_query = "";
        Scanner sc = new Scanner(System.in);
        Map<String, String> bookFields = new HashMap<>();
        bookFields.put("title", "");
        bookFields.put("isbn", "");
        bookFields.put("publication_date", "");
        bookFields.put("edition", "");
        System.out.println("Que campo quieres modificar: ");
        bookFields.forEach((key, value) -> System.out.print("-" + key + " "));
        System.out.print("\nfield: ");
        String field = sc.next();
        System.out.print("\nvalue: ");
        String value = sc.next();
        bookFields.put(field,value);

        return bookFields.entrySet().stream()
                .filter(entry -> !entry.getValue().equals(""))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void updateBook(int id) {
        String SQL_QUERY = "UPDATE books SET " + "WHERE book_id = ?";
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

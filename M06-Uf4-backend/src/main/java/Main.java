import persistence.db.DatabaseDAO;
import persistence.db.DatabaseDAOMysql;
import persistence.models.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        DatabaseDAO db = new DatabaseDAOMysql();
//
//        System.out.println("\nALL BOOKS:");
//        db.listAllBooks().forEach(System.out::println);
//
//        System.out.println("\nSINGLE BOOK:");
//        int id = 7;
//        if (db.findBookById(id).isPresent()) {
//            System.out.println(db.findBookById(id).get());
//        }else {
//            System.out.println("El libro no existe");
//        }
//
//        System.out.println("\nALL BOOKS AFTER DELETE:");
//        db.deleteBook(id);
//        db.listAllBooks().forEach(System.out::println);

        Map<String, String>bookFields = new HashMap<>();
        bookFields.put("book_id", "");
        bookFields.put("title", "titulo de ejemplo");
        bookFields.put("isbn", "");
        bookFields.put("author_id", "1");
        bookFields.put("category_id", "2");
        bookFields.put("publication_date", "25 enero");
        bookFields.put("edition", "");
        bookFields.put("pages", "");
        bookFields.put("quantity", "");
        bookFields.put("price", "");

//
//        Map<String, String>booksFiltered = bookFields.entrySet().stream()
//                .filter(entry -> !entry.getValue().equals(""))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        System.out.println(booksFiltered);
//        System.out.println(booksFiltered.get("title"));
//        booksFiltered.put("title","hola");
//        System.out.println(booksFiltered.get("title"));

        System.out.println( DatabaseDAOMysql.testUpdate());



    }
}

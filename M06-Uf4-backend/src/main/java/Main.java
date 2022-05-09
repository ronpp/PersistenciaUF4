import persistence.db.DatabaseDAO;
import persistence.db.DatabaseDAOMysql;


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
        System.out.println("\nALL BOOKS AFTER DELETE:");
        db.deleteBook(23);
        db.listAllBooks().forEach(System.out::println);


//
//        Map<String, String>booksFiltered = bookFields.entrySet().stream()
//                .filter(entry -> !entry.getValue().equals(""))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        System.out.println(booksFiltered);
//        System.out.println(booksFiltered.get("title"));
//        booksFiltered.put("title","hola");
//        System.out.println(booksFiltered.get("title"));


//        Book newBook = Book.builder()
//                .title("Excel para todos")
//                .isbn("978-2-509-02978-9")
//                .author_id(6)
//                .category_id(5)
//                .publication_date("abril")
//                .edition("2da edicion")
//                .pages("550")
//                .quantity(25)
//                .price(25.46)
//                .build();
//        db.addBook(newBook).forEach(System.out::println);

    }
}

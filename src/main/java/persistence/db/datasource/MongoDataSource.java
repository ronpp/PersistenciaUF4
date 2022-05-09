package persistence.db.datasource;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class MongoDataSource {

    private  static final String URI = "mongodb://192.168.4.30";
    private static MongoClient mongoClient = MongoClients.create(URI);
    private static MongoDatabase database = mongoClient.getDatabase("uf4_db");

    private MongoDataSource(){}

    public static MongoCollection<Document> getCollection(String collectionName){
        return database.getCollection(collectionName);
    }

    public static void populateSampleDataToDatabase(){

        // Categories
        MongoCollection<Document> categories = getCollection("categories");
        Document category_1 = new Document();
        category_1.append("id", 1)
                 .append("name", "Sistemas y Redes");
        Document category_2 = new Document();
        category_2.append("id", 2)
                .append("name", "Desarrollo");
        Document category_3 = new Document();
        category_3.append("id", 3)
                .append("name", "Ofimatica");
        Document category_4 = new Document();
        category_4.append("id", 4)
                .append("name", "Base de Datos");
        categories.insertMany(List.of(category_1, category_2, category_3, category_4));

        // Authors
        MongoCollection<Document> authors = getCollection("authors");
        Document author_1 = new Document();
        author_1.append("id", 1)
                .append("name", "Jerome Gabillaud").append("description","Descripcion Autor 1");
        Document author_2 = new Document();
        author_2.append("id", 2)
                .append("name", "Olivier Heurtel").append("description","Descripcion Autor 2");
        Document author_3 = new Document();
        author_3.append("id", 3)
                .append("name", "Sylvain Hebuterne").append("description","Descripcion Autor 3");
        Document author_4 = new Document();
        author_4.append("id", 4)
                .append("name", "Thierry Groussard").append("description","Descripcion Autor 4");
        authors.insertMany(List.of(author_1, author_2, author_3, author_4));

         // Books
        MongoCollection<Document> books = getCollection("books");
        Document book_1 = new Document();
        book_1.append("id", 1)
                .append("title", "SQL Server 2019")
                .append("isbn", "978-2-409-02980-6")
                .append("author_id",1)
                .append("category_id",4)
                .append("publication_date","Abril 2021")
                .append("edition","1ra Edition")
                .append("pages","498")
                .append("quantity",4)
                .append("price",22.53);

        Document book_2 = new Document();
        book_2.append("id", 2)
                .append("title", "PHP y MySQL")
                .append("isbn", "978-2-409-02434-4")
                .append("author_id",2)
                .append("category_id",2)
                .append("publication_date","Febrero 2020")
                .append("edition","4ta Edition")
                .append("pages","794")
                .append("quantity",4)
                .append("price",19.76);

        Document book_3 = new Document();
        book_3.append("id", 3)
                .append("title", "Desarrolle una aplicacion Android")
                .append("isbn", "978-2-409-01447-5")
                .append("author_id",3)
                .append("category_id",2)
                .append("publication_date","Junio 2018")
                .append("edition","1ra Edition")
                .append("pages","508")
                .append("quantity",15)
                .append("price",25.25);

        Document book_4 = new Document();
        book_4.append("id", 4)
                .append("title", "JAVA 8")
                .append("isbn", "978-2-7460-0934-8")
                .append("author_id",4)
                .append("category_id",2)
                .append("publication_date","Diciembre 2014")
                .append("edition","1ra Edition")
                .append("pages","500")
                .append("quantity",25)
                .append("price",19.76);

        books.insertMany(List.of(book_1, book_2, book_3, book_4));
    }

}

package AccessObjects;
import java.util.List;
import Entity.Book;

public interface AccessBook {
    boolean createBook(Book book);
    List<Book> getAllBooks();
    boolean updateBook(Book book);
    boolean deleteBook(int BookID);
    Book getBookByID(int BookID);
}

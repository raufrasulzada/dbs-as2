package AccessObjects;
import java.util.List;
import Entity.BookAuthorInfo;

public interface AccessBookAuthorInfo {
    boolean createBookAuthorInfo(BookAuthorInfo bookAuthorInfo);
    List<BookAuthorInfo> getAllBookAuthorInfo();
    boolean updateBookAuthorInfo(BookAuthorInfo bookAuthorInfo);
    boolean deleteBookAuthorInfo(int AuthorID, int BookID);
    List<Integer> getBookIDsByAuthor(int AuthorID);
    List<Integer> getAuthorIDsByBook(int BookID);
}

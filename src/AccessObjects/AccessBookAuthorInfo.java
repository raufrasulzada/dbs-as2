package AccessObjects;
import java.util.List;
import Entity.BookAuthorInfo;

public interface AccessBookAuthorInfo {
    public boolean createBookAuthorInfo(BookAuthorInfo bookAuthorInfo);
    public List<BookAuthorInfo> getAllBookAuthorInfo();
    public boolean updateBookAuthorInfo(BookAuthorInfo bookAuthorInfo);
    public boolean deleteBookAuthorInfo(int BookID, int AuthorID);
    public BookAuthorInfo getBookAuthorInfoByBookID(int BookID);
}

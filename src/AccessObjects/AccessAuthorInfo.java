package AccessObjects;
import java.util.List;
import Entity.AuthorInfo;

public interface AccessAuthorInfo {
    public boolean createAuthorInfo(AuthorInfo author);
    public List<AuthorInfo> getAllAuthors();
    public boolean updateAuthorInfo(AuthorInfo author);
    public boolean deleteAuthorInfo(int AuthorID);
    public AuthorInfo getAuthorById(int AuthorID);
}
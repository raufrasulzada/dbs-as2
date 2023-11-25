package Entity;

public class BookAuthorInfo {
    private int AuthorID;
    private int BookID;

    public BookAuthorInfo() {
    }

    public BookAuthorInfo(int AuthorID, int BookID) {
        this.AuthorID = AuthorID;
        this.BookID = BookID;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(int AuthorID) {
        this.AuthorID = AuthorID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    @Override
    public String toString() {
        return "BookAuthorInfo{" +
                "AuthorID=" + AuthorID +
                ", BookID=" + BookID +
                '}';
    }
}

package Entity;

public class BookAuthorInfo {
    private int BookID;
    private int AuthorID;

    public BookAuthorInfo() {
    }

    public BookAuthorInfo(int BookID, int AuthorID) {
        this.BookID = BookID;
        this.AuthorID = AuthorID;
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
                "BookID=" + BookID +
                ", AuthorID=" + AuthorID +
                '}';
    }
}

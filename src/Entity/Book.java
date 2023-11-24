package Entity;

public class Book extends AuthorInfo{
    private int BookID;
    private String Title;
    private int AuthorID;
    private String Edition;
    private String Publisher;
    private int Pages;
    private int Year;
    private double Price;
    private int BooksLeft;

    public Book() {
      
    }

    public Book(int BookID, String Title, int AuthorID, String Edition, String Publisher, int Pages, int Year, double Price, int BooksLeft) {
        this.BookID = BookID;
        this.Title = Title;
        this.AuthorID = AuthorID;
        this.Edition = Edition;
        this.Publisher = Publisher;
        this.Pages = Pages;
        this.Year = Year;
        this.Price = Price;
        this.BooksLeft = BooksLeft;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(int AuthorID) {
        this.AuthorID = AuthorID;
    }

    public String getEdition() {
        return Edition;
    }

    public void setEdition(String Edition) {
        this.Edition = Edition;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public int getPages() {
        return Pages;
    }

    public void setPages(int Pages) {
        this.Pages = Pages;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getBooksLeft() {
        return BooksLeft;
    }

    public void setBooksLeft(int BooksLeft) {
        this.BooksLeft = BooksLeft;
    }

    @Override
    public String toString() {
        return "Book{" +
                "BookID='" + BookID + '\'' +
                ", Title='" + Title + '\'' +
                ", AuthorID=" + AuthorID +
                ", Edition='" + Edition + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", Pages=" + Pages +
                ", Year=" + Year +
                ", Price=" + Price +
                ", BooksLeft=" + BooksLeft +
                '}';
    }
}

package Entity;

public class Book {
    private int BookID;
    private String Title;
    private String Edition;
    private String Publisher;
    private int Pages;
    private int Year;
    private double Price;
    private int BooksLeft;
    private AuthorInfo author;

    public Book() {
      
    }

    public Book(int BookID, String Title, String Edition, String Publisher, int Pages, int Year, double Price, int BooksLeft) {
        this.BookID = BookID;
        this.Title = Title;
        this.Edition = Edition;
        this.Publisher = Publisher;
        this.Pages = Pages;
        this.Year = Year;
        this.Price = Price;
        this.BooksLeft = BooksLeft;
    }

    public Book(int BookID, String Title, String Edition, String Publisher, int Pages, int Year, double Price, int BooksLeft, AuthorInfo author) {
        this.BookID = BookID;
        this.Title = Title;
        this.Edition = Edition;
        this.Publisher = Publisher;
        this.Pages = Pages;
        this.Year = Year;
        this.Price = Price;
        this.BooksLeft = BooksLeft;
        this.author = author;
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

    public AuthorInfo getAuthor() {
        return author;
    }

    public void setAuthor(AuthorInfo author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "BookID='" + BookID + '\'' +
                ", Title='" + Title + '\'' +
                ", Edition='" + Edition + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", Pages=" + Pages +
                ", Year=" + Year +
                ", Price=" + Price +
                ", BooksLeft=" + BooksLeft +
                '}';
    }
}

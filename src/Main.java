import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Connection.abstractConnection;
import Entity.AuthorInfo;
import Methods.AuthorInfoMethods;
import Entity.CustomerInfo;
import Methods.CustomerInfoMethods;

import Entity.Book;
import Methods.BookMethods;
import Entity.OrderInfo;
import MetaData.MetaData;
import Methods.OrderInfoMethods;
import Entity.BookOrderInfo;
import Methods.BookOrderInfoMethods;

import Entity.BookAuthorInfo;
import Entity.BookMoreDetailed;
import Methods.BookAuthorInfoMethods;

public class Main {
    
    static Scanner sc = new Scanner(System.in);
    static AuthorInfoMethods authorInfoMethods = new AuthorInfoMethods() {};
    static CustomerInfoMethods customerInfoMethods = new CustomerInfoMethods() {};
    static BookMethods bookMethods = new BookMethods() {};
    static OrderInfoMethods orderInfoMethods = new OrderInfoMethods() {};
    static BookOrderInfoMethods bookOrderInfoMethods = new BookOrderInfoMethods() {};
    static BookAuthorInfoMethods bookAuthorInfoMethods = new BookAuthorInfoMethods() {};

    public static void main(String[] args) throws SQLException {
        commandLineInterface();
    }

    public static void commandLineInterface() throws SQLException {
        while (true) {
            System.out.println();
            System.out.println("Select the table that you want to make operations: ");
            System.out.println("""
                    1. AuthorInfo Table
                    2. CustomerInfo Table
                    3. Book Table
                    4. OrderInfo Table
                    5. BookOrderInfo Table
                    6. BookAuthorInfo Table
                    7. Show Table Names and Columns
                    8. Show Column Details
                    9. Show Primary Keys
                    10. Show Foreign Keys
                    11. Exit""");
            int userInput = sc.nextInt();
            sc.nextLine();
            switch (userInput) {
                case 1:
                    authorInfoCLI();
                case 2:
                    customerInfoCLI();
                case 3:
                    bookCLI();
                case 4:
                    orderInfoCLI();
                case 5:
                    bookOrderInfoCLI();
                case 6:
                    bookAuthorInfoCLI();
                case 7:
                    MetaData.displayTableNamesAndColumns();
                    commandLineInterface();
                case 8:
                    MetaData.displayColumnDetails();
                    commandLineInterface();
                case 9:
                    MetaData.displayPrimaryKeys();
                    commandLineInterface();
                case 10:
                    MetaData.displayForeignKeys();
                    commandLineInterface();
                case 11:
                    terminate();
                default:
                    System.out.println("This choice does not exist!");
                    commandLineInterface();
            }
        }
    }

        public static void authorInfoCLI() throws SQLException {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("""
                1. Create Author
                2. Retrieve All Authors
                3. Update Author
                4. Delete Author
                5. Retrieve Author by AuthorID
                6. Back""");
            int userInput = sc.nextInt();
            System.out.println();
            switch (userInput) {
            case 1:
                System.out.print("Enter AuthorID: ");
                int AuthorID = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter AuthorName: ");
                String AuthorName = sc.nextLine();
                authorInfoMethods.createAuthorInfo(new AuthorInfo(AuthorID, AuthorName));
                System.out.println();
                authorInfoCLI();
            case 2:
                System.out.println();
                System.out.println(authorInfoMethods.getAllAuthors());
                System.out.println();
                authorInfoCLI();
            case 3:
                System.out.println();
                List<AuthorInfo> authors = authorInfoMethods.getAllAuthors();
                if (authors.size() == 0) {
                    authorInfoCLI();
                }
                System.out.println();
                System.out.print("Enter the AuthorID to Update the Author Information: ");
                int AuthorIDforUpdate = sc.nextInt();
                sc.nextLine();
                System.out.println();
                AuthorInfo author = authorInfoMethods.getAuthorById(AuthorIDforUpdate);
                if (author == null) {
                    System.out.println();
                    authorInfoCLI();
                }
                System.out.print("Update AuthorName: ");
                String AuthorNametoUpdate = sc.nextLine();
                if (AuthorNametoUpdate.trim().length() < 1) {
                    AuthorNametoUpdate = author.getAuthorName();
                }
                author.setAuthorName(AuthorNametoUpdate);
                System.out.println();
                authorInfoMethods.updateAuthorInfo(author);
                System.out.println();
                authorInfoCLI();
                case 4:
                    System.out.print("Enter the AuthorID to Delete the Author: ");
                    int AuthorIDtoDelete = sc.nextInt();
                    System.out.println();
                    authorInfoMethods.deleteAuthorInfo(AuthorIDtoDelete);
                    System.out.println();
                    authorInfoCLI();
                case 5:
                    System.out.print("Enter the AuthorID: ");
                    int AuthorIDforGet = sc.nextInt();
                    System.out.println();
                    System.out.println(authorInfoMethods.getAuthorById(AuthorIDforGet));
                    System.out.println();
                    authorInfoCLI();
            case 6:
                commandLineInterface();
            default:
                System.out.println("This choice does not exist!");
                authorInfoCLI();
        }
    }

    public static void customerInfoCLI() throws SQLException {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("""
                1. Create Customer
                2. Retrieve All Customers
                3. Update Customer
                4. Delete Customer
                5. Retrieve Customer by CustomerID
                6. Back""");
            int userInput = sc.nextInt();
            System.out.println();
            switch (userInput) {
            case 1:
                System.out.print("Enter CustomerID: ");
                int CustomerID = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter CustomerName: ");
                String CustomerName = sc.nextLine();
                customerInfoMethods.createCustomerInfo(new CustomerInfo(CustomerID, CustomerName));
                System.out.println();
                customerInfoCLI();
            case 2:
                System.out.println();
                System.out.println(customerInfoMethods.getAllCustomers());
                System.out.println();
                customerInfoCLI();
            case 3:
                System.out.println();
                List<CustomerInfo> customers = customerInfoMethods.getAllCustomers();
                if (customers.size() == 0) {
                    customerInfoCLI();
                }
                System.out.println();
                System.out.print("Enter the CustomerID to Update the Customer Information: ");
                int CustomerIDforUpdate = sc.nextInt();
                sc.nextLine();
                System.out.println();
                CustomerInfo customer = customerInfoMethods.getCustomerById(CustomerIDforUpdate);
                if (customer == null) {
                    System.out.println();
                    customerInfoCLI();
                }
                System.out.print("Update CustomerName: ");
                String CustomerNametoUpdate = sc.nextLine();
                if (CustomerNametoUpdate.trim().length() < 1) {
                    CustomerNametoUpdate = customer.getCustomerName();
                }
                customer.setCustomerName(CustomerNametoUpdate);
                System.out.println();
                customerInfoMethods.updateCustomerInfo(customer);
                System.out.println();
                customerInfoCLI();
                case 4:
                    System.out.print("Enter the CustomerID to Delete the Customer: ");
                    int CustomerIDtoDelete = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    customerInfoMethods.deleteCustomerInfo(CustomerIDtoDelete);
                    System.out.println();
                    customerInfoCLI();
                case 5:
                    System.out.print("Enter the CustomerID: ");
                    int CustomerIDforGet = sc.nextInt();
                    System.out.println();
                    System.out.println(customerInfoMethods.getCustomerById(CustomerIDforGet));
                    System.out.println();
                    customerInfoCLI();
            case 6:
                commandLineInterface();
            default:
                System.out.println("This choice does not exist!");
                customerInfoCLI();
        }
    }
    public static void bookCLI() throws SQLException {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("""
                1. Create Book
                2. Retrieve All Books
                3. Update Book
                4. Delete Book
                5. Retrieve Book by BookID
                6. Retrieve Whole Detailed Book Information
                7. Back""");
            int userInput = sc.nextInt();
            System.out.println();
            switch (userInput) {
            case 1:
                System.out.print("Enter BookID: ");
                int BookID = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Title: ");
                String Title = sc.nextLine();
                System.out.println("Enter Edition: ");
                String Edition = sc.nextLine();
                System.out.println("Enter Publisher: ");
                String Publisher = sc.nextLine();
                System.out.println("Enter Pages: ");
                int Pages = sc.nextInt();
                System.out.println("Enter Year: ");
                int Year = sc.nextInt();
                System.out.println("Enter Price: ");
                double Price = sc.nextDouble();
                System.out.println("Enter BooksLeft: ");
                int BooksLeft = sc.nextInt();
                System.out.println();
                bookMethods.createBook(new Book(BookID, Title, Edition, Publisher, Pages, Year, Price, BooksLeft));
                bookCLI();
            case 2:
                System.out.println();
                System.out.println(bookMethods.getAllBooks());
                System.out.println();
                bookCLI();
            case 3:
                System.out.println();
                List<Book> books = bookMethods.getAllBooks();
                if (books.size() == 0) {
                    bookCLI();
                }
                System.out.println();
                System.out.print("Enter the BookID to Update the Book Information: ");
                int BookIDforUpdate = sc.nextInt();
                sc.nextLine();
                System.out.println();
                Book book = bookMethods.getBook(BookIDforUpdate);
                if (book == null) {
                    System.out.println();
                    bookCLI();
                }
                System.out.print("Update Title: ");
                String TitletoUpdate = sc.nextLine();
                if (TitletoUpdate.trim().length() < 1) {
                    TitletoUpdate = book.getTitle();
                }
                System.out.println("Update Edition: ");
                String EditiontoUpdate = sc.nextLine();
                if (EditiontoUpdate.trim().length() < 1) {
                    EditiontoUpdate = book.getEdition();
                }
                System.out.println("Update Publisher: ");
                String PublishertoUpdate = sc.nextLine();
                if (PublishertoUpdate.trim().length() < 1) {
                    PublishertoUpdate = book.getPublisher();
                }
                System.out.println("Update Pages: ");
                int PagestoUpdate = sc.nextInt();
                System.out.println("Update Year: ");
                int YeartoUpdate = sc.nextInt();
                System.out.println("Update Price: ");
                double PricetoUpdate = sc.nextDouble();
                System.out.println("Update BooksLeft: ");
                int BooksLefttoUpdate = sc.nextInt();
                book.setTitle(TitletoUpdate);
                book.setEdition(EditiontoUpdate);
                book.setPublisher(PublishertoUpdate);
                book.setPages(PagestoUpdate);
                book.setYear(YeartoUpdate);
                book.setPrice(PricetoUpdate);
                book.setPrice(BooksLefttoUpdate);
                System.out.println();
                bookMethods.updateBook(book);
                System.out.println();
                bookCLI();
                case 4:
                    System.out.print("Enter the BookID to Delete the Book: ");
                    int BookIDtoDelete = sc.nextInt();
                    System.out.println();
                    bookMethods.deleteBook(BookIDtoDelete);
                    System.out.println();
                    bookCLI();
            case 5:
                System.out.print("Enter the BookID to Retrieve the Book: ");
                int BookIdtoGet = sc.nextInt();
                System.out.println();
                System.out.println(bookMethods.getBook(BookIdtoGet));
                System.out.println();
                bookCLI();
            case 6:
                System.out.println();
                System.out.println(bookMethods.retrieveAllBookInfo());
                System.out.println();
                bookCLI();
            case 7:
                commandLineInterface();
            default:
                System.out.println("This choice does not exist!");
                bookCLI();
        }
    }
    public static void orderInfoCLI() throws SQLException {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("""
                1. Create Order Information
                2. Retrieve All Order Information
                3. Update Order Information
                4. Delete Order Information
                5. Retrieve Order Information by OrderID
                6. Back""");
            int userInput = sc.nextInt();
            System.out.println();
            switch (userInput) {
            case 1:
                System.out.print("Enter OrderID: ");
                int OrderID = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter CustomerID: ");
                int CustomerID = sc.nextInt();
                orderInfoMethods.createOrderInfo(new OrderInfo(OrderID, CustomerID));
                System.out.println();
                orderInfoCLI();
            case 2:
                System.out.println();
                System.out.println(orderInfoMethods.getAllOrders());
                System.out.println();
                orderInfoCLI();
            case 3:
                System.out.println();
                List<OrderInfo> orders = orderInfoMethods.getAllOrders();
                if (orders.size() == 0) {
                    orderInfoCLI();
                }
                System.out.println();
                System.out.print("Enter the OrderID to Update the CustomerID: ");
                int OrderIDforUpdate = sc.nextInt();
                sc.nextLine();
                System.out.println();
                OrderInfo order = orderInfoMethods.getOrderById(OrderIDforUpdate);
                if (order == null) {
                    System.out.println();
                    orderInfoCLI();
                }
                System.out.print("Update CustomerID: ");
                int CustomerIDtoUpdate = sc.nextInt();
                order.setCustomer(CustomerIDtoUpdate);
                System.out.println();
                orderInfoMethods.updateOrderInfo(order);
                System.out.println();
                orderInfoCLI();
                case 4:
                System.out.print("Enter the OrderID to Delete the Order Information: ");
                int OrderIDtoDelete = sc.nextInt();
                System.out.println();
                orderInfoMethods.deleteOrderInfo(OrderIDtoDelete);
                System.out.println();
                orderInfoCLI();
                case 5:
                    System.out.print("Enter the OrderID: ");
                    int OrderIDforGet = sc.nextInt();
                    System.out.println();
                    System.out.println(orderInfoMethods.getOrderById(OrderIDforGet));
                    System.out.println();
                    orderInfoCLI();
                case 6:
                commandLineInterface();
            default:
                System.out.println("This choice does not exist!");
                orderInfoCLI();
        }
    }
    public static void bookOrderInfoCLI() throws SQLException {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("""
                1. Create Book Order Information
                2. Retrieve All Book Order Information
                3. Update Book Order Information
                4. Delete Book Order Information
                5. Retrieve Book Order Information by OrderID and BookID
                6. Retrieve Book Order Information by OrderID
                7. Back""");
            int userInput = sc.nextInt();
            System.out.println();
            switch (userInput) {
            case 1:
                System.out.print("Enter OrderID: ");
                int OrderID = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter BookID: ");
                int BookID = sc.nextInt();
                System.out.println("Enter PlacedOrders: ");
                int PlacedOrders = sc.nextInt();
                bookOrderInfoMethods.createBookOrderInfo(new BookOrderInfo(OrderID, BookID, PlacedOrders));
                System.out.println();
                bookOrderInfoCLI();
            case 2:
                System.out.println();
                System.out.println(bookOrderInfoMethods.getAllBookOrders());
                System.out.println();
                bookOrderInfoCLI();
            case 3:
                System.out.println();
                List<BookOrderInfo> orders = bookOrderInfoMethods.getAllBookOrders();
                if (orders.size() == 0) {
                    bookOrderInfoCLI();
                }
                System.out.println();
                System.out.print("Enter the OrderID to Update the Book Order Information: ");
                int OrderIDtoUpdate = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the BookID to Update the Book Order Information: ");
                int BookIDtoUpdate = sc.nextInt();
                BookOrderInfo order = bookOrderInfoMethods.getBookOrderInfo(OrderIDtoUpdate, BookIDtoUpdate);
                if (order == null) {
                    System.out.println();
                    bookOrderInfoCLI();
                }
                System.out.print("Update PlacedOrders: ");
                int PlacedOrdersToUpdate = sc.nextInt();
                order.setPlacedOrders(PlacedOrdersToUpdate);
                System.out.println();
                bookOrderInfoMethods.updateBookOrderInfo(order);
                System.out.println();
                bookOrderInfoCLI();
                case 4:
                System.out.print("Enter the OrderID to Delete the Order Information: ");
                int OrderIDtoDelete = sc.nextInt();
                System.out.println("Enter the BookID to Delete the Order Information: ");
                int BookIDtoDelete = sc.nextInt();
                System.out.println();
                bookOrderInfoMethods.deleteBookOrderInfo(OrderIDtoDelete, BookIDtoDelete);
                System.out.println();
                bookOrderInfoCLI();
                case 5:
                    System.out.print("Enter the OrderID: ");
                    int OrderIDforGet = sc.nextInt();
                    System.out.print("Enter the BookID: ");
                    int BookIDforGet = sc.nextInt();
                    System.out.println();
                    System.out.println(bookOrderInfoMethods.getBookOrderInfo(OrderIDforGet, BookIDforGet));
                    System.out.println();
                    bookOrderInfoCLI();
                case 6:
                    System.out.println("Enter the OrderID");
                    int OrderIdforGet = sc.nextInt();
                    System.out.println(bookOrderInfoMethods.getBookOrderByOrderID(OrderIdforGet));
                    System.out.println();
                    bookOrderInfoCLI();
                case 7:
                commandLineInterface();
            default:
                System.out.println("This choice does not exist!");
                bookOrderInfoCLI();
        }
    }

    public static void bookAuthorInfoCLI() throws SQLException {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("""
                1. Create Book Author Information
                2. Retrieve All Book Author Information
                3. Update Book Author Information
                4. Delete Book Author Information
                5. Retrieve Book Author Information by BookID
                6. Back""");
            int userInput = sc.nextInt();
            System.out.println();
            switch (userInput) {
            case 1:
                System.out.print("Enter AuthorID: ");
                int AuthorID = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter BookID: ");
                int BookID = sc.nextInt();
                bookAuthorInfoMethods.createBookAuthorInfo(new BookAuthorInfo(AuthorID, BookID));
                System.out.println();
                bookAuthorInfoCLI();
            case 2:
                System.out.println();
                System.out.println(bookAuthorInfoMethods.getAllBookAuthorInfo());
                System.out.println();
                bookAuthorInfoCLI();
            case 3:
                System.out.println();
                List<BookAuthorInfo> bookauthorinfo = bookAuthorInfoMethods.getAllBookAuthorInfo();
                if (bookauthorinfo.size() == 0) {
                    bookAuthorInfoCLI();
                }
                System.out.println();
                System.out.print("Enter the BookID to Update the AuthorID: ");
                int BookIDtoUpdate = sc.nextInt();
                sc.nextLine();
                System.out.println();
                BookAuthorInfo bookauthorinf = bookAuthorInfoMethods.getBookAuthorInfoByBookID(BookIDtoUpdate);
                if (bookauthorinf == null) {
                    System.out.println();
                    bookAuthorInfoCLI();
                }
                System.out.print("Update AuthorID: ");
                int AuthorIDtoUpdate = sc.nextInt();
                bookauthorinf.setAuthorID(AuthorIDtoUpdate);
                System.out.println();
                bookAuthorInfoMethods.updateBookAuthorInfo(bookauthorinf);
                System.out.println();
                bookAuthorInfoCLI();
                case 4:
                    System.out.print("Enter the BookID to Delete the Book Author Information: ");
                    int BookIDtoDelete = sc.nextInt();
                    System.out.println("Enter the AuthorID to Delete the Book Author Information");
                    int AuthorIDtoDelete = sc.nextInt();
                    System.out.println();
                    bookAuthorInfoMethods.deleteBookAuthorInfo(BookIDtoDelete, AuthorIDtoDelete);
                    System.out.println();
                    bookAuthorInfoCLI();
            case 5:
                System.out.print("Enter the BookID to get the AuthorID: ");
                int BookIDforGet = sc.nextInt();
                System.out.println();
                System.out.println(bookAuthorInfoMethods.getBookAuthorInfoByBookID(BookIDforGet));
                System.out.println();
                bookAuthorInfoCLI();
            case 6:
                commandLineInterface();
            default:
                System.out.println("This choice does not exist!");
                bookAuthorInfoCLI();
        }
    }

    public static void terminate() throws SQLException {
        System.out.println("If you would like to terminate the application, type 'terminate': ");
        String termination = sc.nextLine();
        if (termination.equalsIgnoreCase("terminate")) {
            System.exit(0);
        } else {
            commandLineInterface();
        }
    }
}

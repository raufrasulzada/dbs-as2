import java.util.List;

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

    public static void main(String[] args) {
        // Create the instances
        AuthorInfoMethods authorInfoMethods = new AuthorInfoMethods() {};
        CustomerInfoMethods customerInfoMethods = new CustomerInfoMethods() {};
        BookMethods bookMethods = new BookMethods() {};
        OrderInfoMethods orderInfoMethods = new OrderInfoMethods() {};
        BookOrderInfoMethods bookOrderInfoMethods = new BookOrderInfoMethods() {};
        BookAuthorInfoMethods bookAuthorInfoMethods = new BookAuthorInfoMethods() {};

        // Implement the createAuthorInfo method
        // BookOrderInfo orderInfoAdd = new BookOrderInfo(4, 1, 1);
        // boolean creationResult = bookOrderInfoMethods.createBookOrderInfo(orderInfoAdd);
        // System.out.println("Creation result: " + creationResult);
        
        
        
        // // Implement the getAllAuthors method
        List<BookMoreDetailed> retrievedBook = bookMethods.retrieveAllBookInfo();
        System.out.println("All info: " + retrievedBook);

        // Implement the updateAuthorInfo method
        // BookOrderInfo bookInf = new BookOrderInfo(3, 2, 5);
        // boolean updateResult = bookOrderInfoMethods.createBookOrderInfo(bookInf);
        // System.out.println("Update result: " + updateResult);

        // Implement the deleteAuthorInfo method
        // int OrderID = 4;
        // int BookID = 1;
        // boolean deletionResult = bookOrderInfoMethods.deleteBookOrderInfo(OrderID, BookID);
        // System.out.println("Deletion result: " + deletionResult);

        // Implement the getAuthorById method
        // int AuthorIdToRetrieve = 1;
        // AuthorInfo RetrievedAuthor = authorInfoMethods.getAuthorById(AuthorIdToRetrieve);
        // System.out.println("Retrieved: " + RetrievedAuthor);

        // Display table names and columns
        // MetaData.displayTableNamesAndColumns();

        // Display column details
        // MetaData.displayColumnDetails();
  
        // Display primary keys
        // MetaData.displayPrimaryKeys();
  
        // Display foreign keys
        // MetaData.displayForeignKeys();
    }
}

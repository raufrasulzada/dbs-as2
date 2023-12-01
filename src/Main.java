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
        // BookOrderInfo orderInfo = new BookOrderInfo(5, 5, 10);
        // boolean creationResult = bookOrderInfoMethods.createBookOrderInfo(orderInfo);
        // System.out.println("Creation result: " + creationResult);
        
        // Implement the retrieveAllBookInfo method
        // List<AuthorInfo> retrievedAuthor = authorInfoMethods.getAllAuthors();
        // System.out.println("All info: " + retrievedAuthor);

        // Implement the update method
        // BookOrderInfo bookinf = new BookOrderInfo(3, 2, 8);
        // boolean updateResult = bookOrderInfoMethods.updateBookOrderInfo(bookinf);
        // System.out.println("Update result: " + updateResult);

        // Implement the delete method
        // int OrderID = 4;
        // boolean deletionResult = orderInfoMethods.deleteOrderInfo(5);
        // System.out.println("Deletion result: " + deletionResult);

        // Implement the getAuthorById method
        // int AuthorIdToRetrieve = 1;
        // AuthorInfo RetrievedAuthor = authorInfoMethods.getAuthorById(1);
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

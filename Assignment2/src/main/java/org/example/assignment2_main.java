package org.example;

import org.example.crud.AuthorCrud;
import org.example.crud.BookCrud;
import org.example.crud.CustomerCrud;
import org.example.crud.OrderCrud;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Customer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class assignment2_main {
  public static void main(String[] args) throws SQLException {
    // Your existing code for database connection and CRUD instances

    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
      System.out.println("Choose an entity to interact with:");
      System.out.println("1. Author");
      System.out.println("2. Book");
      System.out.println("3. Customer");
      System.out.println("4. Order");
      System.out.println("5. Exit");
      System.out.print("Enter your choice: ");

      int entityChoice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character

      switch (entityChoice) {
        case 1:
          // Logic to interact with Author entity
          Connection con = db_connection.getConnection();
          AuthorCrud authorCrud = new AuthorCrud(con); // Initialization of authorCrud
          showAuthorCrud(scanner, authorCrud); // Call method to handle Book operations


          break;
        case 2:
          // Logic to interact with Book entity
          BookCrud bookCrud = null;
          showBookMenu(scanner, bookCrud); // Call method to handle Book operations
          break;
        case 3:
          CustomerCrud customerCrud = null;
          showCustomerCrud(scanner, customerCrud);
          break;
        case 4:
          OrderCrud OrderCrud = null;
          org.example.crud.OrderCrud orderCrud = null;
          showOrderCrud(scanner, orderCrud);
          break;
        case 5:
          exit = true;
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid choice. Please enter a valid option.");
          break;
      }
    }

    // Close your database connection and resources
    scanner.close();
  }
  private static void showAuthorCrud(Scanner scanner, AuthorCrud authorCrud) throws SQLException {
    boolean exitAuthorMenu = false;

    while (!exitAuthorMenu) {
      System.out.println("Author Menu:");
      System.out.println("1. Create an Author");
      System.out.println("2. Retrieve all Authors");
      System.out.println("3. Update an Author");
      System.out.println("4. Delete an Author");
      System.out.println("5. Back to main menu");
      System.out.print("Enter your choice: ");

      int authorMenuChoice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character

      switch (authorMenuChoice) {
        case 1:
          System.out.println("Enter Author details:");
          System.out.print("Author ID: ");
          int authorId = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character

          System.out.print("Author Name: ");
          String authorName = scanner.nextLine();

          System.out.print("Author Date of Birth (YYYY-MM-DD): ");
          String authorDobStr = scanner.nextLine();

          // Create a new Author object
          Date authorDob = null;
          Author newAuthor = new Author(authorId, authorName, authorDob);

          // Perform the create operation using AuthorCrud class
          try {
            authorCrud.createAuthor(newAuthor);
            System.out.println("Author created successfully!");
          } catch (SQLException e) {
            System.out.println("Error creating the author: " + e.getMessage());
          }
          break;
        case 2: // Retrieve all Authors
          try {
            List<Author> authors = authorCrud.getAllAuthors();

            System.out.println("All Authors:");
            for (Author author : authors) {
              System.out.println("Author ID: " + author.getAuthorId());
              System.out.println("Author Name: " + author.getAuthorNameSurname());
              System.out.println("Author DOB: " + author.getAuthorDob());
              System.out.println("---------------");
            }
          } catch (SQLException e) {
            System.out.println("Error retrieving authors: " + e.getMessage());
          }
          break;
        case 3: System.out.print("Enter Author ID to update: ");
          int authorIdToUpdate = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character

          try {
            Author existingAuthor = authorCrud.getAuthorById(authorIdToUpdate);

            if (existingAuthor != null) {
              System.out.println("Enter updated Author details:");
              System.out.print("Author Name: ");
              String updatedName = scanner.nextLine();

              System.out.print("Author Date of Birth (YYYY-MM-DD): ");
              String updatedDobStr = scanner.nextLine();
              LocalDate updatedDob = LocalDate.parse(updatedDobStr);

              // Update the existing Author object
              existingAuthor.setAuthorNameSurname(updatedName);
              existingAuthor.setAuthorDob(Date.valueOf(updatedDob));

              // Call the update method in AuthorCrud
              authorCrud.updateAuthor(existingAuthor);
              System.out.println("Author updated successfully!");
            } else {
              System.out.println("Author with ID " + authorIdToUpdate + " does not exist.");
            }
          } catch (SQLException e) {
            System.out.println("Error updating author: " + e.getMessage());
          }
          break;
        case 4: // Delete an Author
          System.out.print("Enter Author ID to delete: ");
          int authorIdToDelete = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character

          String query = "DELETE FROM Author WHERE Author_id = ?";

          try (PreparedStatement preparedStatement = db_connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, authorIdToDelete);
            int deletedRows = preparedStatement.executeUpdate();

            if (deletedRows > 0) {
              System.out.println("Author deleted successfully!");
            } else {
              System.out.println("Author with ID " + authorIdToDelete + " does not exist.");
            }
          }
          // Check if the author exists, if yes, call authorCrud.deleteAuthor() with the Author ID
          break;
        case 5: // Exit to main menu
          exitAuthorMenu = true;
          System.out.println("Returning to main menu...");
          break;
        default:
          System.out.println("Invalid choice. Please enter a valid option.");
          break;
      }
    }
  }


  private static void showBookMenu(Scanner scanner, BookCrud bookCrud) throws SQLException {
    boolean exitBookMenu = false;

    while (!exitBookMenu) {
      System.out.println("Book Menu:");
      System.out.println("1. Create a Book");
      System.out.println("2. Retrieve all Books");
      System.out.println("3. Update a Book");
      System.out.println("4. Delete a Book");
      System.out.println("5. Back to main menu");
      System.out.print("Enter your choice: ");

      int bookMenuChoice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character

      switch (bookMenuChoice) {
        case 1:
          // Logic to create a Book
          System.out.println("Enter Book details:");
          System.out.print("Author ID: ");
          int authorId = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character

          System.out.print("Title: ");
          String title = scanner.nextLine();

          System.out.print("Number of Books: ");
          int numBooks = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character

          System.out.print("Price: ");
          BigDecimal price = scanner.nextBigDecimal();
          scanner.nextLine(); // Consume the newline character

          Book newBook = new Book(authorId, title, numBooks, price);
          try {
            bookCrud.createBook(newBook);
            System.out.println("Book created successfully!");
          } catch (SQLException e) {
            System.out.println("Error creating the book: " + e.getMessage());
          }
          break;

        case 2:
          // Logic to retrieve all Books
          try {
            List<Book> books = bookCrud.getAllBooksWithAuthors();
            System.out.println("All Books:");
            for (Book book : books) {
              System.out.println("Book ID: " + book.getBookId());
              System.out.println("Author ID: " + book.getAuthorId());
              System.out.println("Title: " + book.getTitle());
              System.out.println("Number of Books: " + book.getNumBooks());
              System.out.println("Price: " + book.getPrice());
              System.out.println("---------------");
            }
          } catch (SQLException e) {
            System.out.println("Error retrieving books: " + e.getMessage());
          }
          break;

        case 3:
          // Logic to update a Book
          System.out.print("Enter Book ID to update: ");
          int bookIdToUpdate = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character

          Book existingBook = null;
          try {
            if (existingBook != null) {
              System.out.println("Enter updated Book details:");
              System.out.print("Author ID: ");
              int updatedAuthorId = scanner.nextInt();
              scanner.nextLine(); // Consume the newline character

              System.out.print("Title: ");
              String updatedTitle = scanner.nextLine();

              System.out.print("Number of Books: ");
              int updatedNumBooks = scanner.nextInt();
              scanner.nextLine(); // Consume the newline character

              System.out.print("Price: ");
              BigDecimal updatedPrice = scanner.nextBigDecimal();
              scanner.nextLine(); // Consume the newline character

              existingBook.setAuthorId(updatedAuthorId);
              existingBook.setTitle(updatedTitle);
              existingBook.setNumBooks(updatedNumBooks);
              existingBook.setPrice(updatedPrice);

              bookCrud.updateBook(existingBook);
              System.out.println("Book updated successfully!");
            } else {
              System.out.println("Book with ID " + bookIdToUpdate + " does not exist.");
            }
          } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
          }
          break;

        case 4:
          // Logic to delete a Book
          System.out.print("Enter Book ID to delete: ");
          int bookIdToDelete = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character

          try {
            bookCrud.deleteBook(bookIdToDelete);
            System.out.println("Book deleted successfully!");
          } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
          }
          break;

        case 5:
          exitBookMenu = true;
          System.out.println("Returning to main menu...");
          break;

        default:
          System.out.println("Invalid choice. Please enter a valid option.");
          break;
      }
    }
  }
  private static void showCustomerCrud(Scanner scanner, CustomerCrud customerCrud) throws SQLException {
    boolean exitCustomerMenu = false;

    while (!exitCustomerMenu) {
      System.out.println("Customer Menu:");
      System.out.println("1. Create a Customer");
      System.out.println("2. Retrieve all Customers");
      System.out.println("3. Update a Customer");
      System.out.println("4. Delete a Customer");
      System.out.println("5. Back to main menu");
      System.out.print("Enter your choice: ");

      int customerMenuChoice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character

      switch (customerMenuChoice) {
        case 1:
          // Logic to create a Customer
          break;
        case 2:
          // Logic to retrieve all Customers
          break;
        case 3:
          // Logic to update a Customer
          break;
        case 4:
          // Logic to delete a Customer
          break;
        case 5:
          exitCustomerMenu = true;
          System.out.println("Returning to main menu...");
          break;
        default:
          System.out.println("Invalid choice. Please enter a valid option.");
          break;
      }
    }
  }
  private static void showOrderCrud(Scanner scanner, OrderCrud orderCrud) throws SQLException {
    boolean exitOrderMenu = false;

    while (!exitOrderMenu) {
      System.out.println("Order Menu:");
      System.out.println("1. Create an Order");
      System.out.println("2. Retrieve all Orders");
      System.out.println("3. Update an Order");
      System.out.println("4. Delete an Order");
      System.out.println("5. Back to main menu");
      System.out.print("Enter your choice: ");

      int orderMenuChoice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character

      switch (orderMenuChoice) {
        case 1:
          // Logic to create an Order
          break;
        case 2:
          // Logic to retrieve all Orders
          break;
        case 3:
          // Logic to update an Order
          break;
        case 4:
          // Logic to delete an Order
          break;
        case 5:
          exitOrderMenu = true;
          System.out.println("Returning to main menu...");
          break;
        default:
          System.out.println("Invalid choice. Please enter a valid option.");
          break;
      }
    }
  }


}



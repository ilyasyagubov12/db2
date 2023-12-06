package org.example.crud;

import org.example.entity.Book;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookCrud {
    private Connection connection;

    public BookCrud(Connection connection) {
        this.connection = connection;
    }

    public void createBook(Book book) throws SQLException {
        String query = "INSERT INTO Books (Author_id, Title, Num_books, Price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, book.getAuthorId());
            pstmt.setString(2, book.getTitle());
            pstmt.setInt(3, book.getNumBooks());
            pstmt.setBigDecimal(4, book.getPrice());
            pstmt.executeUpdate();
        }
    }

    public List<Book> getAllBooksWithAuthors() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT B.*, A.Author_name_surname FROM Books B " +
                "INNER JOIN Authors A ON B.Author_id = A.Author_id";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int bookId = resultSet.getInt("Book_id");
                int authorId = resultSet.getInt("Author_id");
                String title = resultSet.getString("Title");
                int numBooks = resultSet.getInt("Num_books");
                BigDecimal price = resultSet.getBigDecimal("Price");
                String authorName = resultSet.getString("Author_name_surname");

                books.add(new Book(bookId, authorId, title, numBooks, price));
            }
        }
        return books;
    }

    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE Books SET Author_id = ?, Title = ?, Num_books = ?, Price = ? WHERE Book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, book.getAuthorId());
            pstmt.setString(2, book.getTitle());
            pstmt.setInt(3, book.getNumBooks());
            pstmt.setBigDecimal(4, book.getPrice());
            pstmt.setInt(5, book.getBookId());
            pstmt.executeUpdate();
        }
    }

    public void deleteBook(int bookId) throws SQLException {
        String query = "DELETE FROM Books WHERE Book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        }
    }


}

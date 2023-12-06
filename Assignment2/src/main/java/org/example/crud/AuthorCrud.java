package org.example.crud;

import org.example.entity.Author;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrud {
    private Connection connection;

    // Constructor receiving the database connection
    public AuthorCrud(Connection connection) {
        this.connection = connection;
    }

    // Create (Inserting new author record)
    public void createAuthor(Author author) throws SQLException {
        String query = "INSERT INTO Authors (Author_name_surname, Author_dob) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, author.getAuthorNameSurname());
            pstmt.setDate(2, author.getAuthorDob());
            pstmt.executeUpdate();
        }
    }

    // Retrieve (Getting all authors)
    public List<Author> getAllAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM Authors";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int authorId = resultSet.getInt("Author_id");
                String authorName = resultSet.getString("Author_name_surname");
                java.sql.Date authorDob = resultSet.getDate("Author_dob");
                authors.add(new Author(authorId, authorName, authorDob));
            }
        }
        return authors;
    }

    // Update (Updating author details)
    public void updateAuthor(Author author) throws SQLException {
        String query = "UPDATE Authors SET Author_name_surname = ?, Author_dob = ? WHERE Author_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, author.getAuthorNameSurname());
            pstmt.setDate(2, new java.sql.Date(author.getAuthorDob().getTime()));
            pstmt.setInt(3, author.getAuthorId());
            pstmt.executeUpdate();
        }
    }

    // Delete (Removing an author)
    public void deleteAuthor(int authorId) throws SQLException {
        String query = "DELETE FROM Authors WHERE Author_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, authorId);
            pstmt.executeUpdate();
        }
    }

    public Author getAuthorById(int authorId) throws SQLException {
        Author author = null;
        String query = "SELECT * FROM Author WHERE Author_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, authorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("Author_id");
                    String name = resultSet.getString("Author_name_surname");
                    // Assuming Author_dob is a Date field in the database
                    java.sql.Date dobSql = resultSet.getDate("Author_dob");
                    LocalDate dob = dobSql.toLocalDate();

                    author = new Author(id, name, dob);
                }
            }
        }

        return author;
    }
}

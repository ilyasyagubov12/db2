package org.example.entity;
import java.sql.Date;
import java.time.LocalDate;

public class Author {
    private int authorId;
    private String authorNameSurname;
    private Date authorDob;

    // Constructors, getters, and setters
    // You can generate these using your IDE or manually define them

    // Constructor
    public Author(int authorId, String authorNameSurname, Date authorDob) {
        this.authorId = authorId;
        this.authorNameSurname = authorNameSurname;
        this.authorDob = authorDob;
    }

    public Author(int id, String name, LocalDate dob) {
    }

    // Getters and Setters
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorNameSurname() {
        return authorNameSurname;
    }

    public void setAuthorNameSurname(String authorNameSurname) {
        this.authorNameSurname = authorNameSurname;
    }

    public Date getAuthorDob() {
        return authorDob;
    }

    public void setAuthorDob(Date authorDob) {
        this.authorDob = authorDob;
    }



}

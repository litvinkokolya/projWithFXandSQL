package com.example.projwithfxandsql;

public class Book {
    private String nameOfBook;
    private String genreOfBook;
    private String authorOfBook;

    public Book() {}

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getGenreOfBook() {
        return genreOfBook;
    }

    public Book(String nameOfBook, String genreOfBook, String authorOfBook) {
        this.nameOfBook = nameOfBook;
        this.genreOfBook = genreOfBook;
        this.authorOfBook = authorOfBook;
    }

    public void setGenreOfBook(String genreOfBook) {
        this.genreOfBook = genreOfBook;
    }

    public String getAuthorOfBook() {
        return authorOfBook;
    }

    public void setAuthorOfBook(String authorOfBook) {
        this.authorOfBook = authorOfBook;
    }
}

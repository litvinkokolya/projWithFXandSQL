package com.example.projwithfxandsql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class DatabaseHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public void SQLaddBook(Book book){
        String insert = "INSERT INTO " + Const.BOOK_TABLE + " (" +
                Const.BOOK_NAME + "," + Const.BOOK_GENRE + "," + Const.BOOK_AUTHOR + ")" +
                "VALUES(?, ?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, book.getNameOfBook());
            prSt.setString(2, book.getGenreOfBook());
            prSt.setString(3, book.getAuthorOfBook());

            prSt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void SQLDeleteBook(Book book){
        String delete = "DELETE FROM " + Const.BOOK_TABLE + " WHERE " + Const.BOOK_NAME +
                "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setString(1, book.getNameOfBook());

            prSt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getBookOfGenre(Book book){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.BOOK_TABLE + " WHERE " +
                Const.BOOK_GENRE + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, book.getGenreOfBook());

            resSet = prSt.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }

    public ResultSet getBookOfAuthor(Book book){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.BOOK_TABLE + " WHERE " +
                Const.BOOK_AUTHOR + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, book.getAuthorOfBook());

            resSet = prSt.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }

    public ResultSet getBookOfName(Book book){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.BOOK_TABLE + " WHERE " +
                Const.BOOK_NAME + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, book.getNameOfBook());

            resSet = prSt.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }
    }
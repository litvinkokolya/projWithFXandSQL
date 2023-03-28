package com.example.projwithfxandsql;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.*;
import java.awt.Font;
import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.ResultSet;

public class HelloController extends JFrame{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button findWithName;

    @FXML
    private TextField forSortAuthor;

    @FXML
    private TextField forSortGenre;

    @FXML
    private ImageView imageButtonHome;

    @FXML
    private ImageView imageButtonInfoBook;

    @FXML
    private TextField nameForFindBook;

    @FXML
    private Button sortWithAuthor;

    @FXML
    private Button sortWithGenre;

    @FXML
    void initialize() {
        // по нажатию на рисунок книжки переходит на приложение добавления книги
        imageButtonInfoBook.setOnMouseClicked((MouseEvent e) -> {
            imageButtonInfoBook.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("addbook.fxml"));

            try{
                loader.load();
            }catch (IOException exception){
                exception.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        // по нажатию на логотип переходит на хоум страницу
        imageButtonHome.setOnMouseClicked((MouseEvent e) -> {
            imageButtonHome.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));

            try{
                loader.load();
            }catch (IOException exception){
                exception.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        // СОРТИРОВКА ПО ЖАНРУ
        sortWithGenre.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            String genreOfBook = forSortGenre.getText().trim();
            Book book = new Book();
            book.setGenreOfBook(genreOfBook);
            ResultSet result = dbHandler.getBookOfGenre(book);

            StringBuilder columnValue = new StringBuilder();

            if(!genreOfBook.equals("")) {
                JTextArea area1 = new JTextArea(25, 50);
                area1.setTabSize(10);
                area1.setFont(new Font("Dialog", Font.PLAIN, 14));
                area1.setLineWrap(false);
                area1.setWrapStyleWord(true);
                JPanel contents = new JPanel();
                contents.add(new JScrollPane(area1));
                setContentPane(contents);
                while (true) {
                    try {
                        if (!result.next()) break;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        columnValue.append(result.getString(2)).append(" ".repeat(50 - result.getString(2).length())).append(result.getString(4)).append("\n\n");
                        area1.setText(String.valueOf(columnValue));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                setSize(600,400);
                setVisible(true);
            }
            else {
                Shake SortGenreAnim = new Shake(forSortGenre);
                SortGenreAnim.playAnim();
            }
        });

        // ВЫВОД КНИГ ПО АВТОРУ
        sortWithAuthor.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            String authorOfBook = forSortAuthor.getText().trim();
            Book book = new Book();
            book.setAuthorOfBook(authorOfBook);
            ResultSet result = dbHandler.getBookOfAuthor(book);

            StringBuilder columnValue = new StringBuilder();

            if (!authorOfBook.equals("")){
                JTextArea area1 = new JTextArea(25, 50);
                area1.setTabSize(10);
                area1.setFont(new Font("Dialog", Font.PLAIN, 14));
                area1.setLineWrap(false);
                area1.setWrapStyleWord(true);
                JPanel contents = new JPanel();
                contents.add(new JScrollPane(area1));
                setContentPane(contents);
                while (true) {
                    try {
                        if (!result.next()) break;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        columnValue.append(result.getString(2)).append("       ").append(result.getString(3)).append("\n");
                        area1.setText(String.valueOf(columnValue));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                setSize(600,400);
                setVisible(true);
            }
            else {
                Shake SortAuthorAnim = new Shake(forSortAuthor);
                SortAuthorAnim.playAnim();
            }
        });
// Есть ли такая книга по названию?
        findWithName.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            String nameOfBook = nameForFindBook.getText().trim();
            Book book = new Book();
            book.setNameOfBook(nameOfBook);
            ResultSet result = dbHandler.getBookOfName(book);
            int counter = 0;

            if(!nameOfBook.equals("")) {
                try {
                    while (result.next()) {
                        counter++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (counter >= 1) {
                    String message = "Да, такая книга имеется.";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Уведомление...",
                            JOptionPane.PLAIN_MESSAGE);
                    ;
                } else {
                    String message = "Такая книга отсутствует.";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Уведомление...",
                            JOptionPane.ERROR_MESSAGE);
                    ;
                }
            }
            else {
                Shake nameCreateAnim = new Shake(nameForFindBook);
                nameCreateAnim.playAnim();
            }
        });
    }
}
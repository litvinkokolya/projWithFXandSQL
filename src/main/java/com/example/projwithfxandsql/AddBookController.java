package com.example.projwithfxandsql;

import java.io.IOException;
import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javax.swing.*;

public class AddBookController {

    @FXML
    private Button addbook;

    @FXML
    private TextField authorbook;

    @FXML
    private Button buttonForDelete;

    @FXML
    private Button buttonForEdit;

    @FXML
    private TextField genrebook;

    @FXML
    private ImageView imageButtonHome;

    @FXML
    private ImageView imageButtonInfoBook;

    @FXML
    private TextField nameBookForDelete;

    @FXML
    private TextField namebook;

    @FXML
    void initialize() {
        // по нажатию кнопочки метод срабатывает, описан он ниже
        addbook.setOnAction(event -> {
            CreateBook();
        });

        buttonForDelete.setOnAction(event -> {
            DeleteBook();
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
    }

    private void CreateBook() {
        // добавление книги с возможностью подловить на ошибке
        DatabaseHandler dbHandler = new DatabaseHandler();
        String nameOfBook = namebook.getText().trim();
        String genreOfBook = genrebook.getText().trim();
        String authorOfBook = authorbook.getText().trim();


        Book book = new Book(nameOfBook, genreOfBook, authorOfBook);
        if(!nameOfBook.equals("") && !genreOfBook.equals("") && !authorOfBook.equals("")) {
            dbHandler.SQLaddBook(book);
            JOptionPane.showMessageDialog(new JFrame(), "Ура, вы добавили книгу!", "Уведомление...",
                    JOptionPane.PLAIN_MESSAGE);
        }
        else {
            Shake nameCreateAnim = new Shake(namebook);
            Shake genreCreateAnim = new Shake(genrebook);
            Shake authorCreateAnim = new Shake(authorbook);
            nameCreateAnim.playAnim();
            genreCreateAnim.playAnim();
            authorCreateAnim.playAnim();
        }
    }

    private void DeleteBook() {
        // Удаление книги по названию.
        DatabaseHandler dbHandler = new DatabaseHandler();
        String nameOfBook = nameBookForDelete.getText().trim();

        Book book = new Book(nameOfBook, "", "");
        if(!nameOfBook.equals("")){
            dbHandler.SQLDeleteBook(book);
            JOptionPane.showMessageDialog(new JFrame(), "Ура, вы удалили книгу!", "Уведомление...",
                    JOptionPane.PLAIN_MESSAGE);
        }
        else {
            Shake nameBookForDeleteAnim = new Shake(nameBookForDelete);
            nameBookForDeleteAnim.playAnim();
        }
    }
}

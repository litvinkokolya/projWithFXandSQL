package com.example.projwithfxandsql;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonInfoBook;

    @FXML
    private ImageView imageButtonHome;

    @FXML
    private ImageView imageButtonInfoBook;

    @FXML
    void initialize() {
        imageButtonInfoBook.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Clicked!");
        });
    }

}

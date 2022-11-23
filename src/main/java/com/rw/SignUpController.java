package com.rw;

import javafx.event.ActionEvent;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        signUpButton.setOnAction(event -> {
            dbHandler.signUpUser(login_field.getText(), password_field.getText(), 1);
        });

    }

}






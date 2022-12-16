package com.rw;


import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import com.rw.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController implements IDirectToWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    static String CURRENT_USER;


    @FXML
    void initialize() {

        loginSignUpButton.setOnAction(actionEvent -> {
            loginSignUpButton.getScene().getWindow().hide();
            try {
                openHomeScene(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        authSignInButton.setOnAction(ActionEvent -> {
            var alert = new Alert(Alert.AlertType.ERROR);

            if (
                    login_field.getText().isBlank() ||
                            password_field.getText().isEmpty()
            ) {
                alert.setHeaderText("Заполните все поля");
                alert.show();
                return;
            } else {

                String loginText = login_field.getText().trim();
                String loginPassword = password_field.getText().trim();


                var connection = new SocketConnection();

                var user = connection.authorize(loginText, loginPassword);

                CURRENT_USER = user.getUsername();
                if (CURRENT_USER != null) {
                    if (user.getRole() == 1) {
                        authSignInButton.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/FindTicket.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        FindTicketController someApplicationController = loader.getController();

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("");
                        stage.show();

                    }
                    if (user.getRole() == 0) {
                        authSignInButton.getScene().getWindow().hide();
                        authSignInButton.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AdminPanel.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        AdminFlightPanelController someApplicationController = loader.getController();

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("");
                        stage.show();

                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Пользователя не существует");
                    alert.show();
                    return;
                }
            }

        });
    }


    void openHomeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/SignUp.fxml"));
        Parent root = loader.load();
        SignUpController someApplicationController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Железная дорога");
        stage.show();


    }



}


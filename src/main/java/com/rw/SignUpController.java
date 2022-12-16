package com.rw;

import com.rw.Model.User;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController implements IDirectToWindow {

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
        signUpButton.setOnAction(actionEvent -> {
          //  signUpNewUser();
            SocketConnection connection = new SocketConnection();
           var a=  connection.register(login_field.getText(),password_field.getText(), 1);
            System.out.println(a);


            signUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/SignInView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            SignInController someApplicationController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("");
            stage.show();
        });
    }


}






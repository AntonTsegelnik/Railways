package com.rw;

import com.rw.Model.User;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
            System.out.println("signUoController" + a);


            signUpButton.getScene().getWindow().hide();
            openNewScene("view/SignInView.fxml");
        });
    }
    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String username= login_field.getText();
        String password= password_field.getText();
        int role= 1;
        User user = new User(username,password,role);
        dbHandler.signUpUser(user);
    }

}






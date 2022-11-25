package com.rw;



import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML




    
    void openHomeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/SignUp.fxml"));
        Parent root = loader.load();
        SignUpController someApplicationController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Железная дорога");
        stage.show();

    }


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
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
            } else
                System.out.println("Login and password is empty");

        });
    }



//  @FXML
//  void initialize() {
//
//
//
//
//
//
//
//
//      authSignInButton.setOnAction(ActionEvent -> {
//          String loginText = login_field.getText().trim();
//          String loginPassword = password_field.getText().trim();
//
//          if (!loginText.equals("") && !loginPassword.equals("")) {
//              loginUser(loginText, loginPassword);
//          } else
//              System.out.println("Login and password is empty");
//
//      });
//
//      loginSignUpButton.setOnAction(event -> {
//          openNewScene("view/signUp.fxml", loginSignUpButton);
//
//      });
//
//  }
    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);
        int counter = 0;

            try{
            while(result.next()) {
                counter++;
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    if(counter >= 1){
    openNewScene("view/SignUp.fxml", loginSignUpButton);
    }
  }


}


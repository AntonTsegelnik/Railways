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
//        try{
//            server = new Server(new ServerSocket(1234));
//        } catch (IOException e){
//            e.printStackTrace();
//            System.out.println("error creating server. ");
//        }



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

            var connection = new SocketConnection();
           CURRENT_USER =  connection.authorize(loginText, loginPassword);
           if(CURRENT_USER != null) {
               authSignInButton.getScene().getWindow().hide();
               openNewScene("view/FindTicket.fxml");
           }
           else {
               System.out.println("Error");
           }
//        authSignInButton.setOnAction(ActionEvent -> {
//            String loginText = login_field.getText().trim();
//            String loginPassword = password_field.getText().trim();
//           // curl -X POST -H "Content-Type: application/json" -d '{"title": "My Post1", "body": "post content", "userId": 9}' https://dummyjson.com/posts/add
//
//
//            if (!loginText.equals("") && !loginPassword.equals("")) {
//                loginUser(loginText, loginPassword);
//            } else
//                System.out.println("Login and password is empty");
//
      });
    };

    void openHomeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/SignUp.fxml"));
        Parent root = loader.load();
        SignUpController someApplicationController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Железная дорога");
        stage.show();


    }


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
    openNewScene("view/FindTicket.fxml");
    }
  }


}


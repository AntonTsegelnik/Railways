package com.rw;

import com.rw.Model.FlightsRequest;
import com.rw.Model.Passenger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPassengerController {

    @FXML
    private Button adding_button;

    @FXML
    private Button cancel_button;

    @FXML
    private TextField country_field;

    @FXML
    private TextField firstname_field;

    @FXML
    private TextField id_field;

    @FXML
    private TextField lastname_field;

    @FXML
    private TextField passport_num_field;

    @FXML
    private TextField username_field;

    @FXML
    void initialize() {
        adding_button.setOnAction(Event -> {
            var passenger = new Passenger(firstname_field.getText(), lastname_field.getText(),
                    country_field.getText(), passport_num_field.getText(), Integer.parseInt(id_field.getText()),
                    username_field.getText());
                    var connection = new SocketConnection();
                    connection.addPassenger(passenger);

            adding_button.getScene().getWindow().hide();
        });
        cancel_button.setOnAction(Event -> {
            cancel_button.getScene().getWindow().hide();
        });

    }

}

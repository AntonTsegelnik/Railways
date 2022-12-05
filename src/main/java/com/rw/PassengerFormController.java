package com.rw;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import static com.rw.Model.Const.CHOSEN_FLIGHTS;


public class PassengerFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button booking_button;

    @FXML
    private Button cancel_button;
    @FXML
    private TextField country_field;

    @FXML
    private TextField coupe_field;

    @FXML
    private TextField coupe_price_field;

    @FXML
    private TextField flight_code_field;

    @FXML
    private TextField last_name_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField passport_field;

    @FXML
    private TextField res_field ;

    @FXML
    private TextField res_price_field;

    @FXML
    private TextField seat_field;

    @FXML
    private TextField seat_price_field;

    @FXML
    private ChoiceBox seat_type_field;
    @FXML
    private Text txt;

    @FXML
    void initialize() {
        var connection = new SocketConnection();
        var prices = connection.getPrices(CHOSEN_FLIGHTS.getFlightCode());
        for (var item : prices
             ) {
            if(item.getSeatType().equals("Купейный")){
                this.coupe_price_field.setText(Double.toString(item.getPrice()));
            }
            if(item.getSeatType().equals("Плацкартный")){
                this.res_price_field.setText(Double.toString(item.getPrice()));
            }
            if(item.getSeatType().equals("Сидячий")){
                this.seat_price_field.setText(Double.toString(item.getPrice()));
            }

        }

        this.seat_type_field.setItems(FXCollections.observableArrayList("Купейный","Плацкартный","Сидячий"));

        this.flight_code_field.setText(CHOSEN_FLIGHTS.getFlightCode());
        this.coupe_field.setText(Integer.toString(CHOSEN_FLIGHTS.getCoupe()));
        this.res_field.setText(Integer.toString(CHOSEN_FLIGHTS.getRes()));
        this.seat_field.setText(Integer.toString(CHOSEN_FLIGHTS.getSeats()));

    }

}

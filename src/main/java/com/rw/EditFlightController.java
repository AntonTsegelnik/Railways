package com.rw;

import com.rw.Model.FlightsRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.rw.AdminFlightPanelController.CURRENT_FLIGHT;

public class EditFlightController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adding_button;

    @FXML
    private DatePicker ar_date_field;

    @FXML
    private TextField ar_time_field;

    @FXML
    private Button cancel_button;

    @FXML
    private TextField coupe_field;

    @FXML
    private DatePicker dep_date_field;

    @FXML
    private TextField dep_time_field;

    @FXML
    private TextField flight_code_field;

    @FXML
    private TextField flight_distance_field;

    @FXML
    private TextField rail_from_field;

    @FXML
    private TextField rail_to_field;

    @FXML
    private TextField res_field;

    @FXML
    private TextField seats_field;

    @FXML
    void initialize() {

        if(CURRENT_FLIGHT != null){
            this.flight_code_field.setText(CURRENT_FLIGHT.getFlightCode());
            this.flight_distance_field.setText(Double.toString(CURRENT_FLIGHT.getFlightDistance()));
            this.dep_date_field.setValue(CURRENT_FLIGHT.getDate());
            this.ar_date_field.setValue(CURRENT_FLIGHT.getDateAr());
            this.coupe_field.setText(Integer.toString(CURRENT_FLIGHT.getCoupe()));
            this.res_field.setText(Integer.toString(CURRENT_FLIGHT.getRes()));
            this.seats_field.setText(Integer.toString(CURRENT_FLIGHT.getRes()));
            this.dep_time_field.setText(CURRENT_FLIGHT.getTime());
            this.ar_time_field.setText(CURRENT_FLIGHT.getTimeAr());
            this.rail_from_field.setText(CURRENT_FLIGHT.getWhere());
            this.rail_to_field.setText(CURRENT_FLIGHT.getWhereTo());
        }



        adding_button.setOnAction(Event ->{
            var flight = new FlightsRequest(rail_from_field.getText(),rail_to_field.getText(),
                    (dep_date_field.getValue()), dep_time_field.getText(), ar_time_field.getText(),
                    flight_code_field.getText(),Integer.parseInt(coupe_field.getText()),
                    Integer.parseInt( res_field.getText()),Integer.parseInt( seats_field.getText()),ar_date_field.getValue(),Double.parseDouble(flight_distance_field.getText()));
            var connection = new SocketConnection();
            connection.deleteFlight(CURRENT_FLIGHT.getFlightCode());
            var connection1 = new SocketConnection();
            connection.addFlight(flight);


            adding_button.getScene().getWindow().hide();
        });
        cancel_button.setOnAction(Event->{
            cancel_button.getScene().getWindow().hide();
        });

    }

}

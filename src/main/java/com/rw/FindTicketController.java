package com.rw;

import java.net.URL;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.rw.Model.FlightsRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FindTicketController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker choose_data;

    @FXML
    private Button find_button;

    @FXML
    private TextField where_field;

    @FXML
    private TextField where_to_field;

    @FXML
    void initialize() {
        find_button.setOnAction(actionEvent -> {
            //init
            String where = where_field.getText().trim();
            String whereTo = where_to_field.getText().trim();
            LocalDate localDate = choose_data.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);

            SocketConnection connection = new SocketConnection();


            DatabaseHandler dbHandler = new DatabaseHandler();
            FlightsRequest flightsRequest = new FlightsRequest(where, whereTo, date);

            ResultSet result = dbHandler.getFlight(flightsRequest);



        });

    }

}

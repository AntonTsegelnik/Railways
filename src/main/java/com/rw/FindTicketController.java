package com.rw;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import com.rw.Model.Flights;
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
        find_button.setOnAction(actionEvent->{
            String where = where_field.getText().trim();
            String whereTo = where_to_field.getText().trim();
            Date date = choose_data.getValue().get;


            DatabaseHandler dbHandler = new DatabaseHandler();
            Flights flights = new Flights(where,whereTo,date);

            ResultSet result = dbHandler.getFlight(flights);

            System.out.println(result);

        });

    }

}

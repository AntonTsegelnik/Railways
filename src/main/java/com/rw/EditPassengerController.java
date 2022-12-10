package com.rw;

import com.rw.Model.Passenger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.rw.AdminFlightPanelController.CURRENT_FLIGHT;
import static com.rw.AdminPassengersPanelController.CURRENT_PAS;

public class EditPassengerController {

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
        setCurrentPassenger();


        adding_button.setOnAction(Event -> {
            var passenger = new Passenger(firstname_field.getText(), lastname_field.getText(),
                    country_field.getText(), passport_num_field.getText(), Integer.parseInt(id_field.getText()),
                    username_field.getText());
                    var coonection1 = new SocketConnection();
                    coonection1.deletePassenger(CURRENT_PAS.getPassId());
                    var connection2 = new SocketConnection();
                    connection2.addPassenger(passenger);

            adding_button.getScene().getWindow().hide();
        });
        cancel_button.setOnAction(Event -> {
            cancel_button.getScene().getWindow().hide();
        });

    }

    private void setCurrentPassenger() {
        if(CURRENT_PAS != null){

            this.id_field.setText(Integer.toString(CURRENT_PAS.getPassId()));
            this.firstname_field.setText(CURRENT_PAS.getFirstName());
            this.lastname_field.setText(CURRENT_PAS.getLastName());
            this.country_field.setText(CURRENT_PAS.getCountry());
            this.username_field.setText(CURRENT_PAS.getUsername());
            this.passport_num_field.setText(CURRENT_PAS.getPassportNum());

        }
    }


}

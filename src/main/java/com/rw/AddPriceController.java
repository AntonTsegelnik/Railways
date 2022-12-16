package com.rw;

import com.rw.Model.Price;
import com.rw.Model.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static com.rw.AdminTicketsPanelController.CURRENT_TIX;

public class AddPriceController {

    @FXML
    private Button adding_button;

    @FXML
    private Button cancel_button;

    @FXML
    private TextArea SeatType;
    @FXML
    private TextArea FlightCode;
    @FXML
    private TextField Price;

    @FXML
    void initialize() {
        setCurrentTixPrice();
        adding_button.setOnAction(Event -> {
            var price = new Price( FlightCode.getText(), SeatType.getText(), Double.parseDouble(Price.getText()));
                    var connection = new SocketConnection();
                    connection.addPrice(price);

            adding_button.getScene().getWindow().hide();
        });
        cancel_button.setOnAction(Event -> {
            cancel_button.getScene().getWindow().hide();
        });

    }
    private void setCurrentTixPrice() {
        if(CURRENT_TIX != null){
            FlightCode.setEditable(false);
            SeatType.setEditable(false);

            this.FlightCode.setText(CURRENT_TIX.getFlightCode());

            this.SeatType.setText(CURRENT_TIX.getSeatType());

        }
    }

}

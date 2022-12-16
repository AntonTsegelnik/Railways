package com.rw;

import com.rw.Model.Passenger;
import com.rw.Model.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.rw.AdminTicketsPanelController.CURRENT_TIX;

public class AddTicketController {

    @FXML
    private Button adding_button;

    @FXML
    private Button cancel_button;

    @FXML
    private TextField TicketCode;

    @FXML
    private TextField TrainCar;

    @FXML
    private TextField SeatNum;

    @FXML
    private TextField SeatType;

    @FXML
    private TextField PassId;

    @FXML
    private TextField FlightCode;

    @FXML
    void initialize() {
        adding_button.setOnAction(Event -> {
            var ticket = new Ticket(Integer.parseInt(TrainCar.getText()), Integer.parseInt(SeatNum.getText()),
                    SeatType.getText(), FlightCode.getText(), Integer.parseInt(TicketCode.getText()),
                    Integer.parseInt(PassId.getText()));
                    var connection = new SocketConnection();
                    connection.addTicket(ticket);

            adding_button.getScene().getWindow().hide();
        });
        cancel_button.setOnAction(Event -> {
            cancel_button.getScene().getWindow().hide();
        });

    }

}

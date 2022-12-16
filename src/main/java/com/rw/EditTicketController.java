package com.rw;

import com.rw.Model.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static com.rw.AdminPassengersPanelController.CURRENT_PAS;
import static com.rw.AdminTicketsPanelController.CURRENT_TIX;

public class EditTicketController {

    @FXML
    private Button adding_button;

    @FXML
    private Button cancel_button;

    @FXML
    private TextArea ticket_code_area;

    @FXML
    private TextField TrainCar;

    @FXML
    private TextField SeatNum;

    @FXML
    private TextField SeatType;

    @FXML
    private TextArea pass_id_area;

    @FXML
    private TextArea flight_code_area;

    @FXML
    void initialize() {
        setCurrentTix();

        adding_button.setOnAction(Event -> {

            var ticket = new Ticket(Integer.parseInt(TrainCar.getText()), Integer.parseInt(SeatNum.getText()),
                    SeatType.getText(), flight_code_area.getText(), Integer.parseInt(ticket_code_area.getText()),
                    Integer.parseInt(pass_id_area.getText()));

                    var connection = new SocketConnection();
                    connection.deleteTicket(CURRENT_TIX.getTicketCode());

                    var connection2 = new SocketConnection();
                    connection.addTicket(ticket);

            adding_button.getScene().getWindow().hide();
        });
        cancel_button.setOnAction(Event -> {
            cancel_button.getScene().getWindow().hide();
        });

    }
    private void setCurrentTix() {
        if(CURRENT_TIX != null){
            pass_id_area.setEditable(false);
            flight_code_area.setEditable(false);
            ticket_code_area.setEditable(false);
            this.pass_id_area.setText(Integer.toString(CURRENT_TIX.getPassId()));
            this.flight_code_area.setText(CURRENT_TIX.getFlightCode());
            this.ticket_code_area.setText(Integer.toString(CURRENT_TIX.getTicketCode()));
            this.TrainCar.setText(Integer.toString(CURRENT_TIX.getTrainCar()));
            this.SeatNum.setText(Integer.toString(CURRENT_TIX.getSeatNum()));
            this.SeatType.setText(CURRENT_TIX.getSeatType());

        }
    }

}

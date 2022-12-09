package com.rw;

import java.net.URL;
import java.util.ResourceBundle;

import com.rw.Model.Passenger;
import com.rw.Model.Ticket;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import static com.rw.Model.Const.CHOSEN_FLIGHTS;
import static com.rw.SignInController.CURRENT_USER;


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
    private TextField res_field;

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

        getPrices();

        this.seat_type_field.setItems(FXCollections.observableArrayList("Купейный", "Плацкартный", "Сидячий"));
        this.flight_code_field.setText(CHOSEN_FLIGHTS.getFlightCode());
        this.coupe_field.setText(Integer.toString(CHOSEN_FLIGHTS.getCoupe()));
        this.res_field.setText(Integer.toString(CHOSEN_FLIGHTS.getRes()));
        this.seat_field.setText(Integer.toString(CHOSEN_FLIGHTS.getSeats()));

        booking_button.setOnAction(EventAction -> {

            var passenger = new Passenger(name_field.getText(), last_name_field.getText(),
                    country_field.getText(), passport_field.getText(), CURRENT_USER);

            passenger.setPassId(setPassenger(passenger));
            bookTicket(passenger);

        });

        cancel_button.setOnAction(EventAction->{
            cancel_button.getScene().getWindow().hide();
        });
    }

    private void bookTicket(Passenger passenger) {

        var ticket = createTicket(passenger);

        var connection = new SocketConnection();
        var bookingTicket = connection.bookTicket(ticket);

    }

    private Ticket createTicket(Passenger passenger) {
        var ticket = new Ticket();
        ticket.setFlightCode(CHOSEN_FLIGHTS.getFlightCode());
        ticket.setPassId(passenger.getPassId());
        if (seat_type_field.getSelectionModel().getSelectedItem().toString().equals("Купейный") && coupe_field.getText() != "0") {
            ticket.setSeatType("Купейный");
            ticket.setTrainCar(1);
            ticket.setSeatNum(Integer.parseInt(coupe_field.getText()));
            this.coupe_field.setText(Integer.toString((ticket.getSeatNum() - 1)));
            //TO DO Change in db
        } else {
            System.out.println("No coupe tickets");
        }
        if (seat_type_field.getSelectionModel().getSelectedItem().toString().equals("Плацкартный") && res_field.getText() != "0") {
            ticket.setSeatType("Плацкартный");
            ticket.setTrainCar(2);
            ticket.setSeatNum(Integer.parseInt(res_field.getText()));
            this.res_field.setText(Integer.toString((ticket.getSeatNum() - 1)));
            //TO DO Change in db
        } else {
            System.out.println("No res tickets");
        }
        if (seat_type_field.getSelectionModel().getSelectedItem().toString().equals("Сидячий") && seat_field.getText() != "0") {
            ticket.setSeatType("Сидячий");
            ticket.setTrainCar(3);
            ticket.setSeatNum(Integer.parseInt(seat_field.getText()));
            this.seat_field.setText(Integer.toString((ticket.getSeatNum() - 1)));
            //TO DO Change in db
        } else {
            System.out.println("No seats tickets");
        }
        return ticket;
    }


    private int setPassenger(Passenger passenger) {

        var connection = new SocketConnection();
        int res = connection.setPassenger(passenger);
        return res;
    }

    private void getPrices() {
        var connection = new SocketConnection();
        var prices = connection.getPrices(CHOSEN_FLIGHTS.getFlightCode());
        for (var item : prices
        ) {
            if (item.getSeatType().equals("Купейный")) {
                this.coupe_price_field.setText(Double.toString(item.getPrice()));
            }
            if (item.getSeatType().equals("Плацкартный")) {
                this.res_price_field.setText(Double.toString(item.getPrice()));
            }
            if (item.getSeatType().equals("Сидячий")) {
                this.seat_price_field.setText(Double.toString(item.getPrice()));
            }
        }
    }


}

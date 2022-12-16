package com.rw;

import com.rw.Model.Price;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static com.rw.AdminTicketsPanelController.CURRENT_TIX;

public class AddPriceMainController {

    @FXML
    private Button adding_button;

    @FXML
    private Button cancel_button;

    @FXML
    private ChoiceBox SeatType;
    @FXML
    private TextField FlightCode;
    @FXML
    private TextField Price;

    @FXML
    void initialize() {
        this.SeatType.setItems(FXCollections.observableArrayList("Купейный", "Плацкартный", "Сидячий"));
        adding_button.setOnAction(Event -> {
            var price = new Price(FlightCode.getText(), SeatType.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(Price.getText()));
            var connection = new SocketConnection();
            connection.addPrice(price);

            adding_button.getScene().getWindow().hide();
        });
        cancel_button.setOnAction(Event -> {
            cancel_button.getScene().getWindow().hide();
        });

    }

}

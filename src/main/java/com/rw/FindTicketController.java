package com.rw;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.rw.Model.FlightsRequest;
import com.rw.Model.ServerFlightsResponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FindTicketController {

    public static ArrayList<ServerFlightsResponse> DATA_ABOUT_FLIGHTS = null;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker choose_data;

    @FXML
    private Button find_button;
    @FXML
    private Button my_tickets_button;
    @FXML
    private TextField where_field;

    @FXML
    private TextField where_to_field;


    @FXML
    void initialize() {
        find_button.setOnAction(actionEvent -> {
            if (
                    where_field.getText().isBlank() ||
                            where_to_field.getText().isEmpty()
            ) {
                var alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Заполните все поля");
                alert.show();
                return;
            }
            else {

                //init
                String where = where_field.getText().trim();
                String whereTo = where_to_field.getText().trim();
                LocalDate date = choose_data.getValue();

                SocketConnection connection = new SocketConnection();
                DATA_ABOUT_FLIGHTS = connection.findTickets(date, where, whereTo);
                if(DATA_ABOUT_FLIGHTS.size() == 0){
                    var alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Рейс не найден");
                    alert.show();
                    return;

                }
                else {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("view/ShowFlights.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }
            }
        });
        my_tickets_button.setOnAction(actionEvent -> {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/ShowMyTickets.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

    }

}

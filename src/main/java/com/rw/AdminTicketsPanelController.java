package com.rw;

import com.rw.Model.FlightsRequest;
import com.rw.Model.Passenger;
import com.rw.Model.Ticket;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AdminTicketsPanelController implements IDirectToWindow {
    private ObservableList<Ticket> tickets = FXCollections.observableArrayList();

    @FXML
    private TableView<Ticket> tableTickets;

    @FXML
    private TableColumn<Ticket, Integer> TrainCar;

    @FXML
    private TableColumn<Ticket, Integer> SeatNum;

    @FXML
    private TableColumn<Ticket, String> SeatType;
    @FXML
    private TableColumn<Ticket, String> FlightCode;
    @FXML
    private TableColumn<Ticket, Integer> TicketCode;
    @FXML
    private TableColumn<Ticket, Integer> PassId;


    @FXML
    private TextField search_field;

    @FXML
    private Button add_button;

    @FXML
    private ChoiceBox choose_table;

    @FXML
    private ChoiceBox choose_column;

    @FXML
    private Button delete_button;

    @FXML
    private Button edit_button;

    @FXML
    private Button find_button;

    @FXML
    private Button show_table_button;

    @FXML
    private TableView<Passenger> tablePassengers;

    static Passenger CURRENT_PAS;
    static FlightsRequest CURRENT_FLIGHT;


    // инициализируем форму данными
    @FXML
    private void initialize() {


        this.choose_column.setItems(FXCollections.observableArrayList("Код рейса", "Код билета", "Тип места"));
        this.choose_table.setItems(FXCollections.observableArrayList("Рейсы", "Пассажиры", "Билеты", "Бронь"));
        this.choose_table.setValue("Билеты");
        showTable();
        //show
        show_table_button.setOnAction(Event -> {

            if (choose_table.getSelectionModel().getSelectedIndex() == 0) {

                show_table_button.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AdminPanel.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                var someApplicationController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("");
                stage.show();
            }

            if (choose_table.getSelectionModel().getSelectedIndex() == 1) {

                show_table_button.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AdminPassengersPanel.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                var someApplicationController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("");
                stage.show();
                showTable();

            }


            if (choose_table.getSelectionModel().getSelectedIndex() == 2) {

                showTable();
            }

        });

        // TableView.TableViewSelectionModel<Passenger> selectionModel = tablePassengers.getSelectionModel();
//        selectionModel.selectedItemProperty().addListener(new ChangeListener<Passenger>() {
//            @Override
//            public void changed(ObservableValue<? extends Passenger> observableValue, Passenger flightsRequest, Passenger t1) {
//                if (t1 != null)
//                    CURRENT_PAS = t1;
//            }
//        });

//        delete_button.setOnAction(Event -> {
//            var connection = new SocketConnection();
//            passengers = FXCollections.observableArrayList(connection.deletePassenger(CURRENT_PAS.getPassId()));
//            tablePassengers.setItems(passengers);
//        });
//
//        add_button.setOnAction(Event -> {
//            openNewScene("view/AddPassengerForm.fxml");
//        });
//        edit_button.setOnAction(Event -> {
//            openNewScene("view/EditPassengerForm.fxml");
//        });
//        find_button.setOnAction(Event -> {
//            var search = choose_column.getSelectionModel().getSelectedItem().toString();
//            var textIn = search_field.getText();
//            ArrayList<Passenger> findPas = new ArrayList<>();
//            var con = new SocketConnection();
//            var pasen = con.getPassengers();
//            if (choose_column.getSelectionModel().getSelectedIndex() == 0) {
//                if (pasen != null) {
//                    for (var item : pasen
//                    ) {
//                        if (item.getPassId() == Integer.parseInt(textIn)) {
//                            findPas.add(item);
//                        }
//
//                    }
//                }
//            }
//            if (choose_column.getSelectionModel().getSelectedIndex() == 1) {
//                if (pasen != null) {
//                    for (var item : pasen
//                    ) {
//                        if (item.getPassportNum().equals(textIn)) {
//                            findPas.add(item);
//                        }
//
//                    }
//                }
//            }
//            if (choose_column.getSelectionModel().getSelectedIndex() == 2) {
//                if (pasen != null) {
//                    for (var item : pasen
//                    ) {
//                        if (item.getFirstName().equals(textIn)) {
//                            findPas.add(item);
//                        }
//
//                    }
//                }
//            }
//            passengers = FXCollections.observableArrayList(findPas);
//            tablePassengers.setItems(passengers);
//        });
    }

    public void showTable() {
        var connection = new SocketConnection();
        var tck = connection.getTicketsForAdmin();

        tickets = FXCollections.observableArrayList(tck);

        // устанавливаем тип и значение которое должно хранится в колонке
        TicketCode.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("TicketCode"));
        FlightCode.setCellValueFactory(new PropertyValueFactory<Ticket, String>("FlightCode"));
        TrainCar.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("TrainCar"));
        SeatNum.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("SeatNum"));
        SeatType.setCellValueFactory(new PropertyValueFactory<Ticket, String>("SeatType"));
        PassId.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("PassId"));


        // заполняем таблицу данными
        tableTickets.setItems(tickets);
    }
}
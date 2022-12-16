package com.rw;

import com.rw.Model.Booking;
import com.rw.Model.FlightsRequest;
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
import java.util.ArrayList;


public class AdminBookingPanelController implements IDirectToWindow {
    private ObservableList<Booking> bookings = FXCollections.observableArrayList();

    @FXML
    private TableView<Booking> tableBooking;
    @FXML
    private TableColumn<Booking, Integer> TicketCode;


    @FXML
    private TextField search_field;


    @FXML
    private ChoiceBox choose_table;

    @FXML
    private ChoiceBox choose_column;

    @FXML
    private Button delete_button;

    @FXML
    private Button find_button;

    @FXML
    private Button show_table_button;

    static Booking CURRENT_BOOKING;
    static FlightsRequest CURRENT_FLIGHT;


    // инициализируем форму данными
    @FXML
    private void initialize() {


        this.choose_column.setItems(FXCollections.observableArrayList("Код билета"));
        this.choose_table.setItems(FXCollections.observableArrayList("Рейсы", "Пассажиры", "Билеты", "Цены", "Бронь"));
        this.choose_table.setValue("Бронь");
        showTable();
        //show
        route();

        TableView.TableViewSelectionModel<Booking> selectionModel = tableBooking.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Booking>() {
            @Override
            public void changed(ObservableValue<? extends Booking> observableValue, Booking flightsRequest, Booking t1) {
                if (t1 != null)
                    CURRENT_BOOKING = t1;
            }
        });

        delete_button.setOnAction(Event -> {
            var connection = new SocketConnection();
            var connection2 = new SocketConnection();
            connection.deleteBooking(CURRENT_BOOKING);
            bookings = FXCollections.observableArrayList(connection2.getBookingForAdmin());
            tableBooking.setItems(bookings);
        });


        find_button.setOnAction(Event -> {
            search();
        });

    }

    private void search() {
        var search = choose_column.getSelectionModel().getSelectedItem().toString();
        var textIn = search_field.getText();
        ArrayList<Booking> finBooking = new ArrayList<>();
        var con = new SocketConnection();
        var bookingDb = con.getBookingForAdmin();
        if (choose_column.getSelectionModel().getSelectedIndex() == 0) {
            if (bookingDb != null) {
                for (var item : bookingDb
                ) {
                    if (item.getTicketCode() == Integer.parseInt(textIn)) {
                        finBooking.add(item);
                    }

                }
            }
        }
    bookings =FXCollections.observableArrayList(finBooking);
        tableBooking.setItems(bookings);
}

    private void route() {
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

                show_table_button.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AdminTicketsPanel.fxml"));
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
            if (choose_table.getSelectionModel().getSelectedIndex() == 3) {
                show_table_button.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AdminPricesPanel.fxml"));
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
            if (choose_table.getSelectionModel().getSelectedIndex() == 4) {
                showTable();
            }

        });
    }

    public void showTable() {
        var connection = new SocketConnection();
        var bck = connection.getBookingForAdmin();

        bookings = FXCollections.observableArrayList(bck);

        // устанавливаем тип и значение которое должно хранится в колонке
        TicketCode.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("TicketCode"));



        // заполняем таблицу данными
        tableBooking.setItems(bookings);
    }
}
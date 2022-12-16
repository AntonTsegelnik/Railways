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
    private Button create_price_button;
    @FXML
    private Button addInBookingButton;
    static Ticket CURRENT_TIX;
    static FlightsRequest CURRENT_FLIGHT;


    // инициализируем форму данными
    @FXML
    private void initialize() {


        this.choose_column.setItems(FXCollections.observableArrayList("Код рейса", "Код билета", "Тип места"));
        this.choose_table.setItems(FXCollections.observableArrayList("Рейсы", "Пассажиры", "Билеты", "Цены", "Бронь"));

        this.choose_table.setValue("Билеты");
        showTable();
        //show
        route();

         TableView.TableViewSelectionModel<Ticket> selectionModel = tableTickets.getSelectionModel();
          selectionModel.selectedItemProperty().addListener(new ChangeListener<Ticket>() {
           @Override
           public void changed(ObservableValue<? extends Ticket> observableValue, Ticket flightsRequest, Ticket t1) {
               if (t1 != null)
                   CURRENT_TIX = t1;
           }
       });

       delete_button.setOnAction(Event -> {
           var connection = new SocketConnection();
           tickets = FXCollections.observableArrayList(connection.deleteTicket(CURRENT_TIX.getTicketCode()));
           tableTickets.setItems(tickets);
       });

        addInBookingButton.setOnAction(Event -> {
            var connection = new SocketConnection();
            connection.addTixInBooking(CURRENT_TIX);
            tickets = FXCollections.observableArrayList(connection.getTicketsForAdmin());
            tableTickets.setItems(tickets);
        });
        add_button.setOnAction(Event -> {
            openNewScene("view/AddTicketForm.fxml");
        });
       edit_button.setOnAction(Event -> {
           openNewScene("view/EditTicketForm.fxml");
       });
        find_button.setOnAction(Event -> {
           search();
        });
        create_price_button.setOnAction(Event->{
            openNewScene("view/AddPriceForm.fxml");
        });
    }

    private void search() {
        var search = choose_column.getSelectionModel().getSelectedItem().toString();
        var textIn = search_field.getText();
        ArrayList<Ticket> finTix = new ArrayList<>();
        var con = new SocketConnection();
        var tix = con.getTicketsForAdmin();
        if (choose_column.getSelectionModel().getSelectedIndex() == 0) {
            if (tix != null) {
                for (var item : tix
                ) {
                    if (item.getFlightCode().equals (textIn)) {
                        finTix.add(item);
                    }

                }
            }
        }
        if (choose_column.getSelectionModel().getSelectedIndex() == 1) {
            if (tix != null) {
                for (var item : tix
                ) {
                    if (item.getTicketCode()== Integer.parseInt(textIn)) {
                        finTix.add(item);
                    }

                }
            }
        }
        if (choose_column.getSelectionModel().getSelectedIndex() == 2) {
            if (tix != null) {
                for (var item : tix
                ) {
                    if (item.getSeatType().equals(textIn)) {
                        finTix.add(item);
                    }

                }
            }
        }
        tickets = FXCollections.observableArrayList(finTix);
        tableTickets.setItems(tickets);
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

                showTable();
            }
            if(choose_table.getSelectionModel().getSelectedIndex() == 3){
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
                show_table_button.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AdminBookingPanel.fxml"));
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

        });
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
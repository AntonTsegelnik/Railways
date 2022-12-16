package com.rw;

import com.rw.Model.FlightsRequest;
import com.rw.Model.Ticket;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.rw.FindTicketController.DATA_ABOUT_FLIGHTS;
import static com.rw.Model.Const.CHOSEN_FLIGHTS;


public class MyTicketsController implements IDirectToWindow {
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
    private Button button_delete_ticket;

//TO DO Delete ticket
    // инициализируем форму данными
    @FXML
    private void initialize() {
       var tck = getTickets();
       // initData(tck);
        tickets = FXCollections.observableArrayList(tck);
        Label lbl = new Label();
        // устанавливаем тип и значение которое должно хранится в колонке
        TicketCode.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("TicketCode"));
        FlightCode.setCellValueFactory(new PropertyValueFactory<Ticket, String>("FlightCode"));
        TrainCar.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("TrainCar"));
        SeatNum.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("SeatNum"));
        SeatType.setCellValueFactory(new PropertyValueFactory<Ticket, String>("SeatType"));


        // заполняем таблицу данными
        tableTickets.setItems(tickets);


    }

    private ArrayList<Ticket> getTickets() {

        var connection = new SocketConnection();
        var tickets = connection.getTicket();
        return tickets;

    }

//    private void initData(ArrayList<Ticket> tck) {
//
//        for (var item : tck
//        ) {
//
//            tickets.add(new Ticket(item.getTrainCar(),item.getSeatNum(),item.getSeatType(),item.getFlightCode(),item.getTicketCode()));
//        }
//
//    }
}

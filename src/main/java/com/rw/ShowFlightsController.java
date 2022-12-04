package com.rw;

import com.rw.Model.FlightsRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

import static com.rw.FindTicketController.DATA_ABOUT_FLIGHTS;

public class ShowFlightsController {
    private ObservableList<FlightsRequest> flightsData = FXCollections.observableArrayList();

    @FXML
    private TableView<FlightsRequest> tableUsers;

    @FXML
    private TableColumn<FlightsRequest, String> FromColumn;

    @FXML
    private TableColumn<FlightsRequest, String> ToColumn;

    @FXML
    private TableColumn<FlightsRequest, LocalDate> DateColumn;
    @FXML
    private TableColumn<FlightsRequest, String> TimeColumn;
    @FXML
    private TableColumn<FlightsRequest, String> TimeArColumn;



    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        FromColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("Where"));
        ToColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("WhereTo"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, LocalDate>("Date"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("Time"));
        TimeArColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("TimeAr"));


        // заполняем таблицу данными
        tableUsers.setItems(flightsData);
    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {
        LocalDate localDate;
        for (var item: DATA_ABOUT_FLIGHTS
             ) {
            flightsData.add(new FlightsRequest( item.Where, item.WhereTo, item.Date, item.Time, item.TimeAr));
        }

    }
}

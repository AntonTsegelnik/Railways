package com.rw;

import com.rw.Model.FlightsRequest;
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

import static com.rw.FindTicketController.DATA_ABOUT_FLIGHTS;
import static com.rw.Model.Const.CHOSEN_FLIGHTS;


public class ShowFlightsController implements IDirectToWindow {
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
    @FXML
    private Button button_choose_flight;





    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();
        Label lbl = new Label();
        // устанавливаем тип и значение которое должно хранится в колонке
        FromColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("Where"));
        ToColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("WhereTo"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, LocalDate>("Date"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("Time"));
        TimeArColumn.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("TimeAr"));



        // заполняем таблицу данными
        tableUsers.setItems(flightsData);
        TableView.TableViewSelectionModel<FlightsRequest> selectionModel = tableUsers.getSelectionModel();

        selectionModel.selectedItemProperty().addListener(new ChangeListener<FlightsRequest>() {

            @Override
            public void changed(ObservableValue<? extends FlightsRequest> observableValue, FlightsRequest flightsRequest, FlightsRequest t1) {
                if (t1 != null)
                    CHOSEN_FLIGHTS = t1;
            }
        });
      button_choose_flight.setOnAction(EventAction ->{
          System.out.println(CHOSEN_FLIGHTS.getFlightCode());
          openNewScene("view/PassengerForm.fxml");
      });
    }

    private void initData() {
        LocalDate localDate;
        for (var item : DATA_ABOUT_FLIGHTS
        ) {

            flightsData.add(new FlightsRequest(item.getWhere(), item.getWhereTo(), item.getDate(),
                    item.getTime(), item.getTimeAr(), item.getFlightCode(), item.getCoupe(),
                    item.getRes(), item.getSeats()));
        }

    }
}

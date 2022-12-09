package com.rw;

import com.rw.Model.FlightsRequest;
import com.rw.Model.Passenger;
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


public class AdminPassengersPanelController implements IDirectToWindow {
    private ObservableList<Passenger> passengers = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Passenger, String> FirstName;

    @FXML
    private TableColumn<Passenger, String> LastName;

    @FXML
    private TableColumn<Passenger, Integer> PassId;

    @FXML
    private TableColumn<Passenger, String> Country;

    @FXML
    private TableColumn<Passenger, String> PassportNum;
    @FXML
    private TableColumn<Passenger, String> Username;

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

    static String CURRENT_FC;
    static FlightsRequest CURRENT_FLIGHT;


    // инициализируем форму данными
    @FXML
    private void initialize() {


        this.choose_column.setItems(FXCollections.observableArrayList("Id пассажира", "Номер паспорта", "Имя"));
        this.choose_table.setItems(FXCollections.observableArrayList("Рейсы", "Пассажиры", "Билеты", "Бронь"));
        this.choose_table.setValue("Пассажиры");
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
                AdminFlightPanelController someApplicationController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("");
                stage.show();
            }

            if (choose_table.getSelectionModel().getSelectedIndex() == 1) {

              showTable();

            }
        });

        TableView.TableViewSelectionModel<Passenger> selectionModel = tablePassengers.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Passenger>() {
            @Override
            public void changed(ObservableValue<? extends Passenger> observableValue, Passenger flightsRequest, Passenger t1) {
                if (t1 != null)
                    System.out.println(t1.getUsername());

            }
        });

//        delete_button.setOnAction(Event -> {
//            var connection = new SocketConnection();
//            flights = FXCollections.observableArrayList(connection.deleteFlight(CURRENT_FC));
//            tableFlights.setItems(flights);
//        });

        add_button.setOnAction(Event -> {
            openNewScene("view/AddPassengerForm.fxml");
        });
        edit_button.setOnAction(Event -> {
            openNewScene("view/EditFlightForm.fxml");
        });
        find_button.setOnAction(Event -> {
            var search = choose_column.getSelectionModel().getSelectedItem().toString();
            var textIn = search_field.getText();
            ArrayList<FlightsRequest> findFlights = new ArrayList<>();
            var con = new SocketConnection();
            var flightsDB = con.getFlights();
            if (choose_column.getSelectionModel().getSelectedIndex() == 0) {
                if (flightsDB != null) {
                    for (var item : flightsDB
                    ) {
                        if (item.getFlightCode().equals(textIn)) {
                            findFlights.add(item);
                        }

                    }
                }
            }
            if (choose_column.getSelectionModel().getSelectedIndex() == 1) {
                if (flightsDB != null) {
                    for (var item : flightsDB
                    ) {
                        if (item.getWhere().equals(textIn)) {
                            findFlights.add(item);
                        }

                    }
                }
            }
            if (choose_column.getSelectionModel().getSelectedIndex() == 2) {
                if (flightsDB != null) {
                    for (var item : flightsDB
                    ) {
                        if (item.getWhereTo().equals(textIn)) {
                            findFlights.add(item);
                        }

                    }
                }
            }

//            flights = FXCollections.observableArrayList(findFlights);
//            tableFlights.setItems(flights);
        });
    }
    public void showTable(){
        var connection = new SocketConnection();
        var passengersFromDB = connection.getPassengers();

        passengers = FXCollections.observableArrayList(passengersFromDB);

        FirstName.setCellValueFactory(new PropertyValueFactory<Passenger, String>("FirstName"));
        PassId.setCellValueFactory(new PropertyValueFactory<Passenger, Integer>("PassId"));
        LastName.setCellValueFactory(new PropertyValueFactory<Passenger, String>("LastName"));
        Country.setCellValueFactory(new PropertyValueFactory<Passenger, String>("Country"));
        PassportNum.setCellValueFactory(new PropertyValueFactory<Passenger, String>("PassportNum"));
        Username.setCellValueFactory(new PropertyValueFactory<Passenger, String>("Username"));

        tablePassengers.setItems(passengers);
    }
}
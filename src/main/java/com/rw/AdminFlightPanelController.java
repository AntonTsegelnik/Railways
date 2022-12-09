package com.rw;

import com.rw.Model.FlightsRequest;
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


public class AdminFlightPanelController implements IDirectToWindow {
    private ObservableList<FlightsRequest> flights = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<FlightsRequest, LocalDate> ArrDate;

    @FXML
    private TableColumn<FlightsRequest, String> ArrTime;

    @FXML
    private TableColumn<FlightsRequest, LocalDate> DepDate;

    @FXML
    private TableColumn<FlightsRequest, String> DepTime;

    @FXML
    private TableColumn<FlightsRequest, String> FlightCode;

    @FXML
    private TableColumn<FlightsRequest, Double> FlightDistance;

    @FXML
    private TableColumn<FlightsRequest, Integer> NumCoupe;

    @FXML
    private TableColumn<FlightsRequest, Integer> NumRes;

    @FXML
    private TableColumn<FlightsRequest, Integer> NumSeats;

    @FXML
    private TableColumn<FlightsRequest, String> RailFrom;

    @FXML
    private TableColumn<FlightsRequest, String> RailTo;

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
    private TableView<FlightsRequest> tableFlights;

    static String CURRENT_FC;
    static FlightsRequest CURRENT_FLIGHT;


    // инициализируем форму данными
    @FXML
    private void initialize() {
        this.choose_column.setItems(FXCollections.observableArrayList("Код рейса", "Откуда", "Куда"));
        this.choose_table.setItems(FXCollections.observableArrayList("Рейсы", "Пассажиры", "Билеты", "Бронь"));
        this.choose_table.setValue("Рейсы");
        showTable();

        //show
        show_table_button.setOnAction(Event -> {
            if (choose_table.getSelectionModel().getSelectedIndex() == 0){

               showTable();
            }
            if(choose_table.getSelectionModel().getSelectedIndex() == 1){
                show_table_button.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AdminPassengersPanel.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                AdminPassengersPanelController someApplicationController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("");
                stage.show();

            }

        });

        TableView.TableViewSelectionModel<FlightsRequest> selectionModel = tableFlights.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<FlightsRequest>() {
            @Override
            public void changed(ObservableValue<? extends FlightsRequest> observableValue, FlightsRequest flightsRequest, FlightsRequest t1) {
                if (t1 != null)
                    CURRENT_FC = t1.getFlightCode();
                CURRENT_FLIGHT = t1;
            }
        });

        delete_button.setOnAction(Event -> {
            var connection = new SocketConnection();
            flights = FXCollections.observableArrayList(connection.deleteFlight(CURRENT_FC));
            tableFlights.setItems(flights);
        });

        add_button.setOnAction(Event -> {
            openNewScene("view/AdFlightForm.fxml");
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
                        if(item.getFlightCode().equals(textIn)){
                            findFlights.add(item);
                        }

                    }
                }
            }
            if (choose_column.getSelectionModel().getSelectedIndex() == 1) {
                if (flightsDB != null) {
                    for (var item : flightsDB
                    ) {
                        if(item.getWhere().equals(textIn)){
                            findFlights.add(item);
                        }

                    }
                }
            }
            if (choose_column.getSelectionModel().getSelectedIndex() == 2) {
                if (flightsDB != null) {
                    for (var item : flightsDB
                    ) {
                        if(item.getWhereTo().equals(textIn)){
                            findFlights.add(item);
                        }

                    }
                }
            }

            flights = FXCollections.observableArrayList(findFlights);
            tableFlights.setItems(flights);
        });
    }
    public void showTable(){

        var connection = new SocketConnection();
        var flightsFromDb = connection.getFlights();
        flights = FXCollections.observableArrayList(flightsFromDb);
        FlightCode.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("FlightCode"));
        FlightDistance.setCellValueFactory(new PropertyValueFactory<FlightsRequest, Double>("FlightDistance"));
        DepDate.setCellValueFactory(new PropertyValueFactory<FlightsRequest, LocalDate>("Date"));
        ArrDate.setCellValueFactory(new PropertyValueFactory<FlightsRequest, LocalDate>("DateAr"));
        DepTime.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("Time"));
        ArrTime.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("TimeAr"));
        RailFrom.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("Where"));
        RailTo.setCellValueFactory(new PropertyValueFactory<FlightsRequest, String>("WhereTo"));
        NumCoupe.setCellValueFactory(new PropertyValueFactory<FlightsRequest, Integer>("Coupe"));
        NumRes.setCellValueFactory(new PropertyValueFactory<FlightsRequest, Integer>("Res"));
        NumSeats.setCellValueFactory(new PropertyValueFactory<FlightsRequest, Integer>("Seats"));
        tableFlights.setItems(flights);
    }
}
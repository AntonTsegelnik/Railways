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

    static Passenger CURRENT_PAS;
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
            if (choose_table.getSelectionModel().getSelectedIndex() == 2) {

                show_table_button.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AdminTicketsPanel.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                AdminTicketsPanelController someApplicationController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("");
                stage.show();

            }
        });

        TableView.TableViewSelectionModel<Passenger> selectionModel = tablePassengers.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Passenger>() {
            @Override
            public void changed(ObservableValue<? extends Passenger> observableValue, Passenger flightsRequest, Passenger t1) {
                if (t1 != null)
                    CURRENT_PAS = t1;
            }
        });

        delete_button.setOnAction(Event -> {
            var connection = new SocketConnection();
            passengers = FXCollections.observableArrayList(connection.deletePassenger(CURRENT_PAS.getPassId()));
            tablePassengers.setItems(passengers);
        });

        add_button.setOnAction(Event -> {
            openNewScene("view/AddPassengerForm.fxml");
        });
        edit_button.setOnAction(Event -> {
            openNewScene("view/EditPassengerForm.fxml");
        });
        find_button.setOnAction(Event -> {
            var search = choose_column.getSelectionModel().getSelectedItem().toString();
            var textIn = search_field.getText();
            ArrayList<Passenger> findPas = new ArrayList<>();
            var con = new SocketConnection();
            var pasen = con.getPassengers();
            if (choose_column.getSelectionModel().getSelectedIndex() == 0) {
                if (pasen != null) {
                    for (var item : pasen
                    ) {
                        if (item.getPassId() == Integer.parseInt(textIn)) {
                            findPas.add(item);
                        }

                    }
                }
            }
            if (choose_column.getSelectionModel().getSelectedIndex() == 1) {
                if (pasen != null) {
                    for (var item : pasen
                    ) {
                        if (item.getPassportNum().equals(textIn)) {
                            findPas.add(item);
                        }

                    }
                }
            }
            if (choose_column.getSelectionModel().getSelectedIndex() == 2) {
                if (pasen != null) {
                    for (var item : pasen
                    ) {
                        if (item.getFirstName().equals(textIn)) {
                            findPas.add(item);
                        }

                    }
                }
            }
            passengers = FXCollections.observableArrayList(findPas);
            tablePassengers.setItems(passengers);
        });
    }

    public void showTable() {
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
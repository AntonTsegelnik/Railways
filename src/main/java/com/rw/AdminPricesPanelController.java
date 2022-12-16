package com.rw;

import com.rw.Model.FlightsRequest;
import com.rw.Model.Price;
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


public class AdminPricesPanelController implements IDirectToWindow {
    private ObservableList<Price> prices = FXCollections.observableArrayList();

    @FXML
    private TableView<Price> tablePrices;



    @FXML
    private TableColumn<Price, Double> Price;

    @FXML
    private TableColumn<Price, String> SeatType;
    @FXML
    private TableColumn<Price, String> FlightCode;


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
    @FXML
    private Button create_price_button;
    static com.rw.Model.Price CURRENT_PRICE;
    static FlightsRequest CURRENT_FLIGHT;


    // инициализируем форму данными
    @FXML
    private void initialize() {


        this.choose_column.setItems(FXCollections.observableArrayList("Код рейса", "Тип билета", "Цена"));
        this.choose_table.setItems(FXCollections.observableArrayList("Рейсы", "Пассажиры", "Билеты", "Цены", "Бронь"));
        this.choose_table.setValue("Цены");

        showTable();
        show_table_button.setOnAction(Event -> {

            route();
        });
         TableView.TableViewSelectionModel<Price> selectionModel = tablePrices.getSelectionModel();
          selectionModel.selectedItemProperty().addListener(new ChangeListener<Price>() {
           @Override
           public void changed(ObservableValue<? extends Price> observableValue, Price flightsRequest, Price t1) {
               if (t1 != null)
                   CURRENT_PRICE = t1;
           }
       });

       delete_button.setOnAction(Event -> {
           var connection = new SocketConnection();
           var connection2 = new SocketConnection();
           connection.deletePrice(CURRENT_PRICE);
           prices = FXCollections.observableArrayList(connection2.getPricesForAdmin());
           tablePrices.setItems(prices);
       });

        create_price_button.setOnAction(Event -> {
            openNewScene("view/AddPriceMainForm.fxml");
        });
        find_button.setOnAction(Event -> {
           search();
        });

    }

    private void search() {
        var search = choose_column.getSelectionModel().getSelectedItem().toString();
        var textIn = search_field.getText();
        ArrayList<Price> finPrice = new ArrayList<>();
        var con = new SocketConnection();
        var prc = con.getPricesForAdmin();
        if (choose_column.getSelectionModel().getSelectedIndex() == 0) {
            if (prc != null) {
                for (var item : prc
                ) {
                    if (item.getFlightCode().equals (textIn)) {
                        finPrice.add(item);
                    }

                }
            }
        }
        if (choose_column.getSelectionModel().getSelectedIndex() == 2) {
            if (prc != null) {
                for (var item : prc
                ) {
                    if (item.getPrice() == Double.parseDouble(textIn)) {
                        finPrice.add(item);
                    }

                }
            }
        }
        if (choose_column.getSelectionModel().getSelectedIndex() == 1) {
            if (prc != null) {
                for (var item : prc
                ) {
                    if (item.getSeatType().equals(textIn)) {
                        finPrice.add(item);
                    }

                }
            }
        }
        prices = FXCollections.observableArrayList(finPrice);
        tablePrices.setItems(prices);
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
                showTable();
            }
            if (choose_table.getSelectionModel().getSelectedIndex() == 3) {

                showTable();
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
        var pricesDb = connection.getPricesForAdmin();

        prices = FXCollections.observableArrayList(pricesDb);

        // устанавливаем тип и значение которое должно хранится в колонке
        Price.setCellValueFactory(new PropertyValueFactory<Price, Double>("Price"));
        FlightCode.setCellValueFactory(new PropertyValueFactory<Price, String>("FlightCode"));
        SeatType.setCellValueFactory(new PropertyValueFactory<Price, String>("SeatType"));


        // заполняем таблицу данными
        tablePrices.setItems(prices);
    }
}
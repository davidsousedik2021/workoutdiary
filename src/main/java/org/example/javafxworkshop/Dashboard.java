package org.example.javafxworkshop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Dashboard extends Application {

    RecordManager recordManager;
    Graph graph;
    private DatePicker fromDatePicker;
    private DatePicker toDatePicker;
    private VBox calendarPane = null;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane contentPane = new BorderPane();
        contentPane.setStyle("-fx-background-color: skyblue;");

        fromDatePicker = new DatePicker();
        toDatePicker = new DatePicker();
        recordManager = RecordManager.getInstance();

        String username = "David";
        Label label = new Label("User: " + username);

        BorderPane usernamePane = new BorderPane();
        usernamePane.setRight(label);

        Button addButton = new Button("Add record");
        addButton.setOnAction(actionEvent -> {
            FormDialog.showAddRecordForm(recordManager);
        });

        Button listButton = new Button("List records");
        listButton.setOnAction(actionEvent -> {
            LocalDate fromDate = fromDatePicker.getValue();
            LocalDate toDate = toDatePicker.getValue();
            recordManager.displayRecords(fromDate, toDate);
        });

        Button graphButton = new Button("Show graph");
        graphButton.setOnAction(actionEvent -> {
            LocalDate fromDate = fromDatePicker.getValue();
            LocalDate toDate = toDatePicker.getValue();
            graph.updateChart(recordManager, fromDate, toDate);
        });

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(listButton, graphButton);
        buttons.setPadding(new Insets(25, 0, 0, 0));

        HBox datePickers = new HBox(10);
        datePickers.getChildren().addAll(new Label("Od:"), fromDatePicker, new Label("Do:"), toDatePicker);
        datePickers.setPadding(new Insets(50, 0, 0, 0));

        HBox headerBox = new HBox(10, addButton, usernamePane);
        contentPane.setLeft(headerBox);

        Region spaceBetweenDatePickersAndGraph = new Region();
        VBox.setVgrow(spaceBetweenDatePickersAndGraph, Priority.ALWAYS);
        spaceBetweenDatePickersAndGraph.setMinHeight(50);

        graph = new Graph();

        HBox userControls = new HBox(10, addButton, usernamePane);
        HBox.setHgrow(userControls.getChildren().get(1), Priority.ALWAYS);
        calendarPane = new VBox(5, userControls, datePickers, buttons, spaceBetweenDatePickersAndGraph, graph);
        calendarPane.setPadding(new Insets(10));
        calendarPane.setStyle("-fx-background-color: skyblue;");

        contentPane.setLeft(calendarPane);

        contentPane.setRight(recordManager.getRecordScrollPane());

        Scene scene = new Scene(contentPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}

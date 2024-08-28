package org.example.javafxworkshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    ScrollPane recordScrollPane;
    ListView<VBox> recordListView;
    List<Record> records = new ArrayList<>();

    public RecordManager() {
        recordListView = new ListView<>();
        recordListView.setPrefSize(300, 1000);

        recordScrollPane = new ScrollPane();
        recordScrollPane.setFitToWidth(true);
        recordScrollPane.setPrefHeight(500);

        VBox recordBox = new VBox(10, new Label("Records"), recordListView);
        recordScrollPane.setContent(recordBox);
    }

    public static RecordManager getInstance() {
        return new RecordManager();
    }

    public ScrollPane getRecordScrollPane() {
        return recordScrollPane;
    }

    public void displayRecords(LocalDate fromDate, LocalDate toDate) {
        ObservableList<VBox> items = FXCollections.observableArrayList();

        for (Record record : records) {
            boolean isAfterOrEqualFromDate = fromDate == null || !record.date.isBefore(fromDate);
            boolean isBeforeOrEqualToDate = toDate == null || !record.date.isAfter(toDate);

            if (isAfterOrEqualFromDate && isBeforeOrEqualToDate) {
                VBox recordLayout = new VBox(5);
                recordLayout.getChildren().add(new Label("Date: " + record.date.toString()));
                recordLayout.getChildren().add(new Label("Sport: " + record.sport));
                recordLayout.getChildren().add(new Label("Km: " + record.km));
                recordLayout.getChildren().add(new Label("Hours: " + record.hours));
                recordLayout.getChildren().add(new Label("Description: " + record.description));
                items.add(recordLayout);
            }
        }

        recordListView.setItems(items);
    }

    public void refreshRecords(Record record) {
        records.add(record);
        displayRecords(null, null);
    }

    public List<Record> getRecords() {
        return records;
    }
}

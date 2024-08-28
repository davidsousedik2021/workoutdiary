package org.example.javafxworkshop;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FormDialog {

    public static void showAddRecordForm(RecordManager recordManager) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Fill the form");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        dialog.getDialogPane().setContent(grid);

        DatePicker datePicker = new DatePicker();
        grid.addRow(0, new Label("Date", datePicker));

        ComboBox<String> sportComboBox = new ComboBox<>();
        sportComboBox.getItems().addAll("Running", "Swimming", "Bicycling", "Skiing");
        grid.addRow(1, new Label("Sport:", sportComboBox));

        TextField kmField = new TextField();
        TextField hoursField = new TextField();
        TextArea descriptionArea = new TextArea();

        grid.addRow(2, new Label("Km:", kmField));
        grid.addRow(3, new Label("Hours:", hoursField));
        grid.addRow(4, new Label("Description:", descriptionArea));

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == saveButtonType) {
                return "Save";
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            if(result.equals("Save")) {

                Record record = new Record(datePicker.getValue(), sportComboBox.getValue(), descriptionArea.getText(),
                        Double.valueOf(kmField.getText()), Double.valueOf(hoursField.getText()));
                recordManager.refreshRecords(record);
                System.out.println(record);
            }
        });
    }

}

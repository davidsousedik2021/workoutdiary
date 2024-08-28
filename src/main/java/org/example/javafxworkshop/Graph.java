package org.example.javafxworkshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Graph extends VBox {
    LineChart<String, Number> lineChart;
    NumberAxis yAxis;
    CategoryAxis xAxis;

    public Graph() {
        initializeComponents();
    }

    private void initializeComponents() {
        xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        xAxis.setTickLabelRotation(45);
        xAxis.setTickMarkVisible(true);
        xAxis.setTickLabelsVisible(true);

        yAxis = new NumberAxis();
        yAxis.setLabel("Hours");
        yAxis.setLowerBound(0);
        yAxis.setAutoRanging(false);

        lineChart = new LineChart<>(xAxis, yAxis);

        this.getChildren().add(lineChart);
    }

    public void updateChart(RecordManager recordManager, LocalDate fromDate, LocalDate toDate) {
        lineChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        ObservableList<String> xCategories = FXCollections.observableArrayList();

        double maxKm = 0;

        recordManager.getRecords().sort(Comparator.comparing(record -> record.date));

        for (Record record : recordManager.getRecords()) {
            boolean isAfterOrEqualFromDate = fromDate == null || !record.date.isBefore(fromDate);
            boolean isBeforeOrEqualToDate = toDate == null || !record.date.isAfter(toDate);

            if (isAfterOrEqualFromDate && isBeforeOrEqualToDate) {
                double km = record.km;
                String dateFormatted = record.date.format(DateTimeFormatter.ofPattern("d. MMM yyyy"));

                series.getData().add(new XYChart.Data<>(dateFormatted, km));
                xCategories.add(dateFormatted);

                if (km > maxKm) {
                    maxKm = km;
                }
            }
        }

        xAxis.setCategories(xCategories);

        if (maxKm > 0) {
            yAxis.setUpperBound(maxKm * 1.5);
            yAxis.setTickUnit(Math.ceil(maxKm / 5));
        } else {
            yAxis.setUpperBound(10 * 1.5);
            yAxis.setTickUnit(5);
        }

        lineChart.getData().add(series);
    }

}

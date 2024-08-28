package org.example.javafxworkshop;

import java.time.LocalDate;

public class Record {

    LocalDate date;
    String sport;
    String description;
    double km;
    double hours;

    public Record(LocalDate date, String sport, String description, double km, double hours) {
        this.date = date;
        this.sport = sport;
        this.description = description;
        this.km = km;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Record{" +
                "date=" + date +
                ", sport='" + sport + '\'' +
                ", description='" + description + '\'' +
                ", km=" + km +
                ", hours=" + hours +
                '}';
    }
}

module org.example.javafxworkshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafxworkshop to javafx.fxml;
    exports org.example.javafxworkshop;
}
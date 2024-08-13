module com.example.calculadoragrafica {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calculadoragrafica to javafx.fxml;
    exports com.example.calculadoragrafica;
    exports com.example.calculadoragrafica.MathEngine;
    opens com.example.calculadoragrafica.MathEngine to javafx.fxml;
}
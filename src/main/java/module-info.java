module com.example.prueba {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.example.prueba to javafx.fxml;
    exports com.example.prueba;
    opens archivos.txt to javafx.fxml;
    exports archivos.txt;
}
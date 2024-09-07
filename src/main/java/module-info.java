module org.example.fis_2024 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fis_2024 to javafx.fxml;
    exports org.example.fis_2024;
}
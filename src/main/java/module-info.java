module org.example.fis_2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;

    exports org.example.fis_2024.Practicas;
    opens org.example.fis_2024 to javafx.fxml;
    exports org.example.fis_2024;
}
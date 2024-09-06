package org.example.fis_2024;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.fis_2024.Practicas.Practica1;

import java.io.IOException;

public class HelloApplication extends Application {
    private Scene scene;
    private BorderPane bdpPrincipal;
    private MenuBar mnbPrincipal;
    private Menu menParcial1;
    private MenuItem mitPractica1;
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        CrearUI(stage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/estilos/main.css").toString());
        stage.setMaximized(true);
        stage.show();
    }
    private void CrearUI(Stage stage){
        mitPractica1=new MenuItem("Practica 1");
        mitPractica1.setOnAction(event -> Practica1(stage));
        menParcial1=new Menu("Primer parcial");
        menParcial1.getItems().addAll(mitPractica1);
        mnbPrincipal=new MenuBar();
        mnbPrincipal.getMenus().addAll(menParcial1);
        bdpPrincipal=new BorderPane();
        bdpPrincipal.setTop(mnbPrincipal);
        scene=new Scene(bdpPrincipal);
    }
    private void Practica1(Stage stage){
        new Practica1();
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}
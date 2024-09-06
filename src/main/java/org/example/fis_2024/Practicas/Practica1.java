package org.example.fis_2024.Practicas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica1 extends Stage{
    private Scene escena;
    private BorderPane bdpPrincipal;
    Button btnAgregar=new Button("Agregar");
    private ChoiceBox chbOpcion;

    public Practica1(){
        CrearUI();
        this.setTitle("Práctica 1");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        Label lblBienvenida=new Label("Encuesta de películas");
        lblBienvenida.setId("entrada");

        chbOpcion=new ChoiceBox<>();
        chbOpcion.setValue("Selecciona un género");
        chbOpcion.getItems().addAll("Acción","Comedia","Drama","Fantasía","Musical","Sci-Fi","Suspenso","Terror");

        btnAgregar.setOnAction(event -> probarSalida());

        Label lblEdad=new Label();

        TextField txtEdad=new TextField();
        txtEdad.setMaxWidth(150);
        txtEdad.setPromptText("Ingresa tu edad");
        txtEdad.textProperty().addListener((observable, valorAnt, valorNuevo) ->{
            if(valorNuevo.isEmpty() || valorNuevo.matches("^(1[89]|2[0-9]|30)$")){
                lblEdad.setText("");
                btnAgregar.setDisable(false);
            }else{
                lblEdad.setText("Debe ser un número entre 18 y 30");
                btnAgregar.setDisable(true);
            }
            if(valorNuevo.isEmpty())
                btnAgregar.setDisable(true);
        });


        //btnAgregar.setOnAction();

        VBox vPrincipal=new VBox(lblBienvenida, new Label(), chbOpcion, txtEdad, lblEdad, btnAgregar);
        vPrincipal.setAlignment(Pos.CENTER);
        vPrincipal.setSpacing(15);
        bdpPrincipal=new BorderPane();
        bdpPrincipal.setCenter(vPrincipal);
        escena=new Scene(bdpPrincipal,500,500);
        escena.getStylesheets().add(getClass().getResource("/estilos/main.css").toString());
    }
    private void probarSalida(){
        Double [] ejemplo={10.0,10.0,5.0,15.0,50.0,5.0,5.0,0.0};
        new SalidaDatosPractica1(ejemplo);
    }
}

class SalidaDatosPractica1 extends Stage{
    private Scene escena;
    public SalidaDatosPractica1(Double [] arrResultados){
        CrearUI(arrResultados);
        this.setTitle("Práctica 1");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(Double [] arrResultados){
        ObservableList<PieChart.Data> datos= FXCollections.observableArrayList(
                new PieChart.Data("Acción",arrResultados[0]),
                new PieChart.Data("Comedia",arrResultados[1]),
                new PieChart.Data("Drama",arrResultados[2]),
                new PieChart.Data("Fantasía",arrResultados[3]),
                new PieChart.Data("Musical",arrResultados[4]),
                new PieChart.Data("Sci-Fi",arrResultados[5]),
                new PieChart.Data("Suspenso",arrResultados[6]),
                new PieChart.Data("Terror",arrResultados[7])
        );
        PieChart grafica=new PieChart(datos);
        grafica.setTitle("Géneros preferidos de películas");
        BorderPane bdpPrincipal=new BorderPane();
        bdpPrincipal.setCenter(grafica);
        escena=new Scene(bdpPrincipal,500,500);
    }
}

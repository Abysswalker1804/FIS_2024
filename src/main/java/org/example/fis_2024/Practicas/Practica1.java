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
    private Button btnAgregar=new Button("Agregar");
    private ChoiceBox chbOpcion;
    private double [] Salida=new double[8];
    private double [] Entrada=new double[10];
    private short cont=0;
    private boolean flag=false;
    public Practica1(){
        CrearUI();
        this.setTitle("Práctica 1");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        for(int i=0; i<8; i++)
            Entrada[i]=0.0;
        Label lblBienvenida=new Label("Encuesta de películas");
        lblBienvenida.setId("entrada");

        chbOpcion=new ChoiceBox<>();
        chbOpcion.setValue("Selecciona un género");
        chbOpcion.getItems().addAll("Acción","Comedia","Drama","Fantasía","Musical","Sci-Fi","Suspenso","Terror");

        btnAgregar.setOnAction(event -> probarSalida());
        btnAgregar.setDisable(true);
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
            if(chbOpcion.getValue().toString().equals("Selecciona un género"))
                btnAgregar.setDisable(true);
        });
        chbOpcion.getSelectionModel().selectedIndexProperty().addListener((observable, valorAnt, valorNuevo) ->{
            if(valorNuevo.toString().equals("Selecciona un género")){
                btnAgregar.setDisable(true);
            }else{
                btnAgregar.setDisable(false);
            }
        });
        btnAgregar.setOnAction(event ->{
            String opc=(String)chbOpcion.getValue();
            switch(opc){
                case "Acción":
                    Entrada[cont]=1;
                    break;
                case "Comedia":
                    Entrada[cont]=2;
                    break;
                case "Drama":
                    Entrada[cont]=3;
                    break;
                case "Fantasía":
                    Entrada[cont]=4;
                    break;
                case "Musical":
                    Entrada[cont]=5;
                    break;
                case "Sci-Fi":
                    Entrada[cont]=6;
                    break;
                case "Suspenso":
                    Entrada[cont]=7;
                    break;
                case "Terror":
                    Entrada[cont]=8;
            }
            cont++;
            if(cont>=10){
                Salida=calcularPorcentajes(Entrada);
                new SalidaDatosPractica1(Salida);
                this.close();
            }
            chbOpcion.setValue("Selecciona un género");
            txtEdad.clear();
        });

        VBox vPrincipal=new VBox(lblBienvenida, new Label(), chbOpcion, txtEdad, lblEdad, btnAgregar);
        vPrincipal.setAlignment(Pos.CENTER);
        vPrincipal.setSpacing(15);
        bdpPrincipal=new BorderPane();
        bdpPrincipal.setCenter(vPrincipal);
        escena=new Scene(bdpPrincipal,500,500);
        escena.getStylesheets().add(getClass().getResource("/estilos/main.css").toString());
    }
    private void probarSalida(){
        double [] ejemplo={10.0,10.0,5.0,15.0,50.0,5.0,5.0,0.0};
        new SalidaDatosPractica1(ejemplo);
    }
    public static double[] calcularPorcentajes(double[] arr){
        double[] porcentajes=new double[8];
        double[] generos=new double[10];

        generos[0]=arr[0];
        generos[1]=arr[1];
        generos[2]=arr[2];
        generos[3]=arr[3];
        generos[4]=arr[4];
        generos[5]=arr[5];
        generos[6]=arr[6];
        generos[7]=arr[7];
        generos[8]=arr[8];
        generos[9]=arr[9];

        for(int i=0;i<10;i++){
            if(generos[i]==1)
                porcentajes[0]+=0.1;
            else if(generos[i]==2)
                porcentajes[1]+=0.1;
            else if(generos[i]==3)
                porcentajes[2]+=0.1;
            else if(generos[i]==4)
                porcentajes[3]+=0.1;
            else if(generos[i]==5)
                porcentajes[4]+=0.1;
            else if(generos[i]==6)
                porcentajes[5]+=0.1;
            else if(generos[i]==7)
                porcentajes[6]+=0.1;
            else if(generos[i]==8)
                porcentajes[7]+=0.1;


        }


        return porcentajes;
    }
}

class SalidaDatosPractica1 extends Stage{
    private Scene escena;
    public SalidaDatosPractica1(double [] arrResultados){
        CrearUI(arrResultados);
        this.setTitle("Práctica 1");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(double [] arrResultados){
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

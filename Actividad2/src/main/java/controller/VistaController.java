/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import utils.Hilo;

/**
 * FXML Controller class
 *
 * @author Martín
 */
public class VistaController implements Initializable {

    @FXML
    private TextField txtNumero;
    @FXML
    private Button btnAgregarNumero;
    @FXML
    private Button btnAgregarNumeroAleatorio;
    @FXML
    private Button btnOndenar;
    @FXML
    private Button btnDetener;
    @FXML
    private ListView<String> listLista;
    @FXML
    private RadioButton btnAsc;
    @FXML
    private RadioButton btnDes;
    @FXML
    private ProgressBar barProgreso;
    @FXML
    private Canvas canCanvas;
    
    private ObservableList Listas = FXCollections.observableArrayList();
    Hilo hilo;
    boolean Ordenardo;
    GraphicsContext gc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gc = canCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,450,450);
    }    

    @FXML
    private void AgregarNumero(ActionEvent event) {
        try {
            Listas.add(txtNumero.getText());
            System.out.println(txtNumero.getText());
            System.out.println("");
            listLista.setItems(Listas);
        } catch (Exception e) {
            System.out.println("Paso un error");
        }
    }

    @FXML
    private void AgregarNumeroAleatorio(ActionEvent event) {
        int num = (int) (Math.random()*100);
        String numCad = String.valueOf(num);
        Listas.add(numCad);
        listLista.setItems(Listas);
    }

    @FXML
    private void Ondenar(ActionEvent event) {
        
        int[] vector = new int[Listas.size()];
        try { 
            for(int i=0;i<Listas.size();i++){
            vector[i]= Integer.parseInt((String) Listas.get(i));
            }
            if(btnAsc.isSelected()){
                hilo = new Hilo(vector,true,barProgreso, gc);
                hilo.start();
            }else{
                hilo = new Hilo(vector,false,barProgreso, gc);
                hilo.start();
            }
        } catch (Exception e) {
            System.out.println("Paso un error");
        }
        
        
    }

    @FXML
    private void Detener(ActionEvent event) {
        hilo.stop();
    }    
}

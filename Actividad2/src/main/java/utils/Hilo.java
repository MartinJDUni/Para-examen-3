/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Hilo extends Thread {

    int[] vector;
    boolean orden;
    ProgressBar barraProgreso;
    GraphicsContext lienzo;

    public Hilo (int[] vector, boolean orden, ProgressBar progreso,
       GraphicsContext lienzo) {
        this.vector = vector;
        this.orden = orden;
        this.barraProgreso = progreso;
        this.lienzo = lienzo;
    }

    public int[] getVector() {
       return this.vector;
    }

    @Override
    public void run() {
    // Código necesario para el ordenamiento
        int i,j;
        if(orden){
            for(i=0;i<vector.length;i++){
                for(j=0;j<vector.length-1;j++){
                    pausar(1);
                    pintar(j,j+1,Color.BLUE);
                    if(vector[j]>vector[j+1]){
                        pausar(1);
                        pintar(j,j+1,Color.GREEN);
                        int aux = vector[j];
                        vector[j]=vector[j+1];
                        vector[j+1]= aux;
                    }
                }
            }
        }
    }

    private void pausar(int tiempo) {
       try {
       Thread.sleep(tiempo * 1000);
       } catch (InterruptedException ex) {
       Thread.currentThread().interrupt();
       }
    }

    private void pintar(int v,int j, Color color) {
        int tam = 40;
        int posx= 40, posy=50;
        
        int i=0;
        
        for(int z:vector)
        {
          if(i==v || i==j){
              lienzo.setFill(color);     
          }else{
              lienzo.setFill(Color.RED);
          }
             
          lienzo.fillRect(posx-tam/2,posy-tam/2,tam,tam);
          
          if(i==v || i==j){
              lienzo.setFill(Color.BLACK);     
          }else{
              lienzo.setFill(Color.BLACK);
          }
          lienzo.setFont(new Font(30));
          lienzo.fillText(String.valueOf(z),posx-tam/2,posy+tam/3);
          
          if(posx + (tam+30)<lienzo.getCanvas().getWidth()){
              posx+=50;
          }else{
              posx=40;
              posy+=50;
          }
          i++;
        }
    }

    private void pintarProgreso(int progreso) {
    //Código necesario para actualizar la barra de progreso
        barraProgreso.setProgress((double)progreso/(double)vector.length);
    }
}
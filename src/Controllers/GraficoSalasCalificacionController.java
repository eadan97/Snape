package Controllers;

import Models.Reserva;
import Models.Sala;
import Views.Main;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GraficoSalasCalificacionController {
    public BarChart<String, Number> grpGrafico;
    public NumberAxis yAxis;
    public CategoryAxis xAxis;

    public void initialize(){
        xAxis.setLabel("Sala");
        yAxis.setLabel("Calificación");

        Map<String, Integer> salas=new HashMap<>();

        for (Sala sala: Main.getInstance().salas.getLista()){
                salas.put(sala.getId(), sala.getCalificacion());
        }

        salas=Utils.Utils.sortByValue(salas);

        Iterator iterator=salas.entrySet().iterator();
        Utils.Utils.poblarChart(grpGrafico,iterator,"Cantidad");

    }


}

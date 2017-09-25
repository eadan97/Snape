package Controllers;

import Models.Reserva;
import Views.Main;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.*;

public class GraficoSalasUsoController {
    public BarChart<String, Number> grpGrafico;
    public NumberAxis yAxis;
    public CategoryAxis xAxis;

    public void initialize(){
        xAxis.setLabel("Sala");
        yAxis.setLabel("Veces usada");

        Map<String, Integer> salas=new HashMap<>();

        for (Reserva reserva: Main.getInstance().reservas.getLista()){
            if (salas.containsKey(reserva.getIdSala()))
                salas.put(reserva.getIdSala(), salas.get(reserva.getIdSala())+1);
            else
                salas.put(reserva.getIdSala(), 1);
        }

        salas=Utils.Utils.sortByValue(salas);

        Iterator iterator=salas.entrySet().iterator();
        Utils.Utils.poblarChart(grpGrafico, iterator,"Cantidad");

    }


}

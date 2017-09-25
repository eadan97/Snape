package Controllers;

import Models.Horario;
import Models.Sala;
import Views.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GraficoHorarioUsoController {
    public PieChart grpGrafico;

    public void initialize(){
        /*xAxis.setLabel("Sala");
        yAxis.setLabel("Calificación");
        */
        Map<String, Integer> horarios=new HashMap<>();

        for (Horario horario: Main.getInstance().horarios.getLista()){
            for (Sala sala : Main.getInstance().salas.getLista()){
                if (sala.getAgendaServicio().contains(horario)) {
                    if (horarios.containsKey(horario.toString()))
                        horarios.put(horario.toString(), horarios.get(horario.toString()) + 1);
                    else
                        horarios.put(horario.toString(), 1);
                }

            }
        }
        horarios=Utils.Utils.sortByValue(horarios);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                );


        Iterator iterator=horarios.entrySet().iterator();
        int i=0;
        while (iterator.hasNext()&&i<6){
            Map.Entry entry = (Map.Entry) iterator.next();
            i++;

            pieChartData.add(new PieChart.Data(entry.getKey().toString(), (Integer)entry.getValue()));

        }
        grpGrafico.setData(pieChartData);

    }
}

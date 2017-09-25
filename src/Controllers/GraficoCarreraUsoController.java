package Controllers;

import Models.Estudiante;
import Models.Horario;
import Models.Reserva;
import Models.Sala;
import Models.Wrappers.Estudiantes;
import Utils.Enums.Carrera;
import Views.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GraficoCarreraUsoController {
    public PieChart grpGrafico;

    public void initialize(){
        Map<String, Integer> carreras=new HashMap<>();

        Estudiantes estudiantes = Main.getInstance().estudiantes;

        for (Reserva reserva: Main.getInstance().reservas.getLista()){
            String carrera=estudiantes.estudiantePorId(reserva.getIdOrganizador()).getCarrera();
            if (carreras.containsKey(carrera))
                carreras.put(carrera, carreras.get(carrera) + 1);
            else
                carreras.put(carrera, 1);
        }
        carreras=Utils.Utils.sortByValue(carreras);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                );


        Iterator iterator=carreras.entrySet().iterator();
        int i=0;
        while (iterator.hasNext()&&i<6){
            Map.Entry entry = (Map.Entry) iterator.next();
            i++;

            pieChartData.add(new PieChart.Data(entry.getKey().toString(), (Integer)entry.getValue()));

        }
        grpGrafico.setData(pieChartData);
    }
}

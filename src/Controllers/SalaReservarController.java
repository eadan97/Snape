package Controllers;

import Models.Estudiante;
import Models.Participante;
import Models.Sala;
import Models.Wrappers.Estudiantes;
import Models.Wrappers.Reservas;
import Models.Wrappers.Salas;
import Utils.Utils;
import Views.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class SalaReservarController {
    public TableView tblParticipantes;
    public TableColumn tbcNombre;
    public TableColumn tbcCorreo;
    public TextField txtAsunto;
    public ComboBox cBoxSala;
    public ComboBox cBoxEstudiante;
    public DatePicker datePicker;
    public Spinner spnHora;
    public Spinner spnMinutos;
    public TextField txtNombre;
    public TextField txtCorreo;

    Reservas reservas =Main.getInstance().reservas;
    ArrayList<Participante> participantes=new ArrayList<>();
    Salas salas = Main.getInstance().salas;
    Estudiantes estudiantes=Main.getInstance().estudiantes;

    public void initialize(){
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        poblarSalas();
        poblarEstudiantes();
        refrescarLista();
    }

    private void poblarEstudiantes() {
        for (Estudiante estudiante: estudiantes.getLista()) {
            if (estudiante.getCalificacion()>70
                    &&reservas.cantidadIncidenciasEstudiante(estudiante)<5
                    &&reservas.reservarSemanalesEstudiante(estudiante)<=3)
            cBoxEstudiante.getItems().add(estudiante);
        }
    }

    private void poblarSalas() {
        for (Sala sala: salas.getSalasActivas()) {
            cBoxSala.getItems().add(sala);
        }
    }

    private void refrescarLista() {
        ObservableList<Participante> list = FXCollections.observableArrayList(participantes);
        tblParticipantes.setItems(list);
    }

    public void agregarReserva(ActionEvent actionEvent) {
    }

    public void agregarParticipante(ActionEvent actionEvent) {
        if (!Utils.validarCorreo(txtCorreo.getText())
                ||txtNombre.getText().isEmpty()){
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
            return;
        }
        participantes.add(new Participante(txtNombre.getText(),txtCorreo.getText()));
        refrescarLista();
    }


}

package Controllers;

import Models.Estudiante;
import Models.Incidente;
import Models.Reserva;
import Views.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class EstudianteConsultarController {
    public ComboBox cBoxEstudiante;
    public TableView tblEstudiante;
    public TableColumn tbcEstudianteCarnet;
    public TableColumn tbcEstudianteNombre;
    public TableColumn tbcEstudianteCarrera;
    public TableColumn tbcEstudianteCorreo;
    public TableColumn tbcSalasCalificacion;
    public TableView tblIncidentes;
    public TableColumn tbcExcepcionNombre;
    public TableColumn tbcExcepcionSala;
    public TableColumn tbcExcepcionFecha;
    public TableColumn tbcExcepcionDetalle;
    public TableView tblReservas;
    public TableColumn tbcReservaId;
    public TableColumn tbcReservaAsunto;
    public TableColumn tbcReservaOrganizador;
    public TableColumn tbcReservaEstado;
    public TableColumn tbcReservaFecha;
    public TableColumn tbcReservaInicio;
    public TableColumn tbcReservaFin;
    public TableColumn tbcReservaParticipantes;
    public Estudiante estudiante;

    public void initialize() {
        asignarFactorys();
        poblarEstudiantes();
    }

    private void asignarFactorys() {

        tbcEstudianteCarnet.setCellValueFactory(new PropertyValueFactory<>("carnet"));
        tbcEstudianteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcEstudianteCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        tbcEstudianteCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        tbcSalasCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));
        tbcExcepcionNombre.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tbcExcepcionSala.setCellValueFactory(new PropertyValueFactory<>("sala"));
        tbcExcepcionFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcExcepcionDetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        tbcReservaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcReservaAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        tbcReservaOrganizador.setCellValueFactory(new PropertyValueFactory<>("idOrganizador"));
        tbcReservaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tbcReservaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcReservaInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        tbcReservaFin.setCellValueFactory(new PropertyValueFactory<>("HoraFin"));
        tbcReservaParticipantes.setCellValueFactory(new PropertyValueFactory<>("cantidadParticipantes"));
    }

    private void poblarEstudiantes() {
        for (Estudiante sala: Main.getInstance().estudiantes.getLista()) {
            cBoxEstudiante.getItems().add(sala);
        }
    }

    public void estudianteSeleccionado(ActionEvent actionEvent) {
        estudiante=(Estudiante) cBoxEstudiante.getValue();
        poblarTables();
    }

    private void poblarTables() {
        ObservableList<Estudiante> lEstudiante =FXCollections.observableArrayList(estudiante);
        ObservableList<Incidente>lIncidente=FXCollections.observableArrayList(estudiante.getIncidentes());
        ArrayList<Reserva> reservas = Main.getInstance().reservas.reservasEstudiante(estudiante.getCarnet());
        ObservableList<Reserva>lReserva=FXCollections.observableArrayList(reservas);

        tblEstudiante.setItems(lEstudiante);
        tblIncidentes.setItems(lIncidente);
        tblReservas.setItems(lReserva);
    }
}

package Controllers;

import Models.Horario;
import Models.Reserva;
import Models.Sala;
import Views.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Clase controladora de la vista para consultar vistas.
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class SalasConsultarController {
    public ComboBox cBoxSalas;
    public TableView tblSalas;
    public TableColumn tbcSalasUbicacion;
    public TableColumn tbcSalasId;
    public TableColumn tbcSalasCapacidad;
    public TableColumn tbcSalasRecursos;
    public TableColumn tbcSalasEstado;
    public TableColumn tbcSalasCalificacion;
    public TableView tblHorarios;
    public TableColumn tbcHorarioDia;
    public TableColumn tbcHorarioInicio;
    public TableColumn tbcHorarioFin;
    public TableView tblExcepciones;
    public TableColumn tbcExcepcionNombre;
    public TableColumn tbcExcepcionDia;
    public TableColumn tbcExcepcionInicio;
    public TableColumn tbcExcepcionFin;
    public TableView tblReservas;
    public TableColumn tbcReservaId;
    public TableColumn tbcReservaAsunto;
    public TableColumn tbcReservaOrganizador;
    public TableColumn tbcReservaEstado;
    public TableColumn tbcReservaFecha;
    public TableColumn tbcReservaInicio;
    public TableColumn tbcReservaFin;
    public TableColumn tbcReservaParticipantes;
    public Sala sala;

    /**
     * Metodo de inicio
     */
    public void initialize(){
        poblarSalas();
        asignarFactorys();
    }

    /**
     * Metodo para poblar los TableViews.
     */
    private void poblarTables() {
        ObservableList<Sala> lsala = FXCollections.observableArrayList(sala);
        ObservableList<Horario> listaHorarios = FXCollections.observableArrayList(sala.getAgendaServicio());
        ObservableList<Sala> listaExcepciones = FXCollections.observableArrayList(sala);
        ArrayList<Reserva>reservas=Main.getInstance().reservas.reservasSalaSemanal(sala.getId());
        ObservableList<Reserva> listaReservas = FXCollections.observableArrayList(reservas);
        tblSalas.setItems(lsala);
        tblHorarios.setItems(listaHorarios);
        tblReservas.setItems(listaReservas);
    }

    /**
     * Metodo que se llama al seleccionar una sala.
     * @param actionEvent Boton precionado.
     */
    public void salaSeleccionada(ActionEvent actionEvent) {
        sala=(Sala)cBoxSalas.getValue();
        poblarTables();

    }

    /**
     * Metodo que se llama para asignar los Factorys a cada TableColumn.
     */
    private void asignarFactorys() {
        tbcSalasId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcSalasUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        tbcSalasCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadMaxima"));
        tbcSalasRecursos.setCellValueFactory(new PropertyValueFactory<>("recursos"));
        tbcSalasEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tbcSalasCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));
        tbcHorarioDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        tbcHorarioInicio.setCellValueFactory(new PropertyValueFactory<>("inicio"));
        tbcHorarioFin.setCellValueFactory(new PropertyValueFactory<>("fin"));
        tbcExcepcionNombre.setCellValueFactory(new PropertyValueFactory<>(""));//TODO:Arreglar esto
        tbcExcepcionDia.setCellValueFactory(new PropertyValueFactory<>(""));
        tbcExcepcionInicio.setCellValueFactory(new PropertyValueFactory<>(""));
        tbcExcepcionFin.setCellValueFactory(new PropertyValueFactory<>(""));
        tbcReservaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcReservaAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        tbcReservaOrganizador.setCellValueFactory(new PropertyValueFactory<>("idOrganizador"));
        tbcReservaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tbcReservaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbcReservaInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        tbcReservaFin.setCellValueFactory(new PropertyValueFactory<>("HoraFin"));
        tbcReservaParticipantes.setCellValueFactory(new PropertyValueFactory<>("cantidadParticipantes"));


    }

    /**
     * Metodo para poblar el ComboBox de Salas.
     */
    private void poblarSalas() {
        for (Sala sala: Main.getInstance().salas.getLista()) {
            cBoxSalas.getItems().add(sala);
        }
    }

}

package Controllers;

import Models.Horario;
import Models.Sala;
import Models.Wrappers.Horarios;
import Utils.Utils;
import Views.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.rmi.CORBA.Util;

/**
 * Metodo controlador de la subventa para seleccionar los horarios de una sala.
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class HorarioController {
    public Sala sala;
    public TitledPane tpnHorarios;
    public TableView tblHorarios;
    public TableColumn tbcDia;
    public TableColumn tbcInicio;
    public TableColumn tbcFin;
    public ComboBox cBoxHorarios;
    public Spinner spnInicioHora;
    public Spinner spnInicioMinutos;
    public Spinner spnFinHora;
    public Spinner spnFinMinutos;
    public Horarios horarios;
    public Button btnEnviar;

    /**
     * Metodo de inicio.
     */
    public void initialize(){
        horarios=Main.getInstance().horarios;
        this.sala= SalasCrearController.sala;
        tpnHorarios.setText("Sala: "+sala.getId());

        tbcDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        tbcInicio.setCellValueFactory(new PropertyValueFactory<>("inicio"));
        tbcFin.setCellValueFactory(new PropertyValueFactory<>("fin"));
        poblarHorarios();
        refrescarLista();
    }


    /**
     * Metodo para poblar el ComboBox de horarios.
     */
    private void poblarHorarios(){
        for (Horario horario: horarios.getLista()) {
            cBoxHorarios.getItems().add(horario);
//dia.getDisplayName(TextStyle.FULL, new Locale("es","CR")
        }
    }

    /**
     * Metodo para agregar horarios.
     * @param actionEvent Boton precionado.
     */
    public void agregarHorario(ActionEvent actionEvent) {
        if (cBoxHorarios.getValue()==null
                ||sala.validarHorario((Horario)cBoxHorarios.getValue())){
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
            return;
        }
        sala.agregarHorario((Horario) cBoxHorarios.getValue());
        refrescarLista();

    }

    /**
     * Metodo cerrar la ventana
     * @param actionEvent Boton precionado
     * @throws Exception
     */
    public void enviarHorario(ActionEvent actionEvent) throws Exception {

        if (sala.getAgendaServicio().size()<1){
            Utils.mostrarError("Error", "Error en los horarios seleccionados","Debe seleccionar al menos un horario");
            return;
        }
        Main.getInstance().salas.saveInXML();
        Stage stage = (Stage) btnEnviar.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo para refrescar la lista de horarios seleccionados.
     */
    public void refrescarLista(){
        ObservableList<Horario> list = FXCollections.observableArrayList(sala.getAgendaServicio());
        tblHorarios.setItems(list);
    }

    public void eliminarHorario(ActionEvent actionEvent) {
        if (cBoxHorarios.getValue()==null
                ||!sala.validarHorario((Horario)cBoxHorarios.getValue())){
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
            return;
        }
        sala.eliminarHorario((Horario) cBoxHorarios.getValue());
        refrescarLista();

    }
}

package Controllers;

import Models.Estudiante;
import Models.Participante;
import Models.Sala;
import Models.Wrappers.Estudiantes;
import Models.Wrappers.Reservas;
import Models.Wrappers.Salas;
import Utils.Utils;
import Views.Main;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ReservarSalaController {
    public TableView tblParticipantes;
    public TableColumn tbcNombre;
    public TableColumn tbcCorreo;
    public TextField txtAsunto;
    public ComboBox cBoxSala;
    public ComboBox cBoxOrganizador;
    public DatePicker datePicker;
    public Spinner<Integer> spnHora= new Spinner<>();
    public Spinner<Integer> spnMinutos= new Spinner<>();
    public Spinner<Integer> spnHora1= new Spinner<>();
    public Spinner<Integer> spnMinutos1= new Spinner<>();
    public TextField txtNombre;
    public TextField txtCorreo;
    public TextField txtCapacidad;
    public TextField txtRecurso;
    public Button butAñadirParticipantes;
    public Button butCrearReserva;

    Reservas reservas =Main.getInstance().reservas;
    ArrayList<Participante> participantes=new ArrayList<>();
    Salas salas = Main.getInstance().salas;
    Estudiantes estudiantes=Main.getInstance().estudiantes;

    public void initialize(){
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        
        initSpinner(spnHora,24);
        initSpinner(spnMinutos,59);
        initSpinner(spnHora1,24);
        initSpinner(spnMinutos1,59);
        poblarSalas();
        poblarEstudiantes();
        refrescarLista();
        //validarFecha(datePicker);
    }

    private void poblarEstudiantes() {
        for (Estudiante estudiante: estudiantes.getLista()) {
            if (estudiante.getCalificacion()>70
                    &&reservas.cantidadIncidenciasEstudiante(estudiante)<5
                    &&reservas.reservarSemanalesEstudiante(estudiante)<=3)
            cBoxOrganizador.getItems().add(estudiante.getNombre());
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

    public void validarDatos(ActionEvent actionEvent) {
        if (!validarCamposObligatorios()){
            Utils.mostrarError("Error","Campos obligatorios sin información", "Debe llenar los campos necesarios antes de realizar la reserva.");
            return;}
        if (!validarFecha(datePicker)){
            Utils.mostrarError("Error","Error en los datos ingresados","La fecha ya transcurrió.");
            return;}
        if (!validarHoraInicioFin()){
            Utils.mostrarError("Error","Error en los datos ingresados","La hora de inicio debe ser menor a la de finalización.");
            return;}
        if (!Utils.validarNumero(txtCapacidad.getText())){
            Utils.mostrarError("Error","Error en los datos ingresados","La capacidad mínima debe ser un número entero.");
            return;}
        cBoxSala.setDisable(false);
    }

    public void agregarParticipante(ActionEvent actionEvent) {
        if (!Utils.validarCorreo(txtCorreo.getText())
                ||txtNombre.getText().isEmpty()){
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
            return;
        }
        participantes.add(new Participante(txtNombre.getText(),txtCorreo.getText()));
        refrescarLista();
        txtNombre.clear();
        txtCorreo.clear();
    }

    public void initSpinner(Spinner<Integer> spinner,int intMax){
        final int initialValue=00;
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(00,intMax,initialValue);
        spinner.setValueFactory(valueFactory);
    }
    
    public boolean validarFecha(DatePicker datepicker){
        LocalDate fecha = datePicker.getValue();
        return fecha.compareTo(LocalDate.now())>=0;
    }
    
    public boolean validarHoraInicioFin(){
        int h=spnHora.getValue();
        int m=spnMinutos.getValue();
        int h1=spnHora1.getValue();
        int m1=spnMinutos1.getValue();
        int hora_i=h*100+m;
        int hora_f=h1*100+m1;
        if (hora_f-hora_i>0)
            return true;
        return false;   
    }
    
    public boolean validarCamposObligatorios(){
        if (!"".equals(txtCapacidad.getText())
                && datePicker.getValue()!=null
                && cBoxOrganizador.getSelectionModel().getSelectedItem()!=null)
            return true;
        return false;
    }
    
    public void habilitarParticipantes(ActionEvent actionEvent){
        txtNombre.setDisable(false);
        txtCorreo.setDisable(false);
        butAñadirParticipantes.setDisable(false);
        txtAsunto.setDisable(false);
        butCrearReserva.setDisable(false);   
    }
    
}

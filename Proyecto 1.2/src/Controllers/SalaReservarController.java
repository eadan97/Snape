package Controllers;

import Models.Estudiante;
import Models.Horario;
import Models.Participante;
import Models.Reserva;
import Models.Sala;
import Models.Wrappers.Estudiantes;
import Models.Wrappers.Reservas;
import Models.Wrappers.Salas;
import Utils.Utils;
import Views.Main;
import java.time.LocalDate;
import java.time.LocalTime;
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
    public Label labIdSala;
    public Button butAñadirParticipantes;
    public Button butCrearReserva;
    public Button butIntroducirDatos;
    public Button butCambiarDatos;
    public Button butCambiarSala;
    public ArrayList<Sala> salasAMostrar= new ArrayList<>();

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
        salasDisponibles();
        poblarEstudiantes();
        refrescarListaP();
        //validarFecha(datePicker);
    }

    private void poblarEstudiantes() {
        for (Estudiante estudiante: estudiantes.getLista()) {
            if (estudiante.getCalificacion()>70
                    &&reservas.cantidadIncidenciasEstudiante(estudiante)<5
                    &&reservas.reservasSemanalesEstudiante(estudiante)<=3)
            cBoxOrganizador.getItems().add(estudiante);
        }
    }

    private void poblarSalas() {
        for (Sala sala: salas.getSalasActivas()) {
            cBoxSala.getItems().add(sala);
        }
    }
    
    private void salasDisponibles(){
        for (Sala sala: salasAMostrar){
            cBoxSala.getItems().add(sala);
        }
    }

    private void refrescarListaP() {
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
        cBoxSala.getItems().clear();
        //participantes.clear();
        salasAMostrar.clear();
        discriminarSalas();
        salasDisponibles();
        deshabilitarDatos();
        cBoxSala.setDisable(false);
        labIdSala.setText("ID: ");
    }

    public void agregarParticipante(ActionEvent actionEvent) {
        if (!Utils.validarCorreo(txtCorreo.getText())
                ||txtNombre.getText().isEmpty()){
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
            return;
        }
        participantes.add(new Participante(txtNombre.getText(),txtCorreo.getText()));
        refrescarListaP();
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
        deshabilitarDatos();
        cBoxSala.setDisable(true);
        butCambiarSala.setDisable(false);
        labIdSala.setText("ID: "+cBoxSala.getSelectionModel().getSelectedItem());
    }
    
    public void discriminarSalas(){
            for (Sala sala: salas.getSalasActivas()) {
                if (sala.getCapacidadMaxima()>=Integer.parseInt(txtCapacidad.getText())
                        && (sala.getRecursos().toLowerCase()== txtRecurso.getText().toLowerCase() || sala.getRecursos().toLowerCase().contains(txtRecurso.getText().toLowerCase()) || txtRecurso.getText()==null)){
                    for (Horario horario: sala.getAgendaServicio()){
                        if(horario.getDia()==datePicker.getValue().getDayOfWeek()
                                && (horario.getInicio().getHour()*100+ horario.getInicio().getMinute())<= (spnHora.getValue()*100+ spnMinutos.getValue())
                                && (horario.getFin().getHour()*100+ horario.getFin().getMinute())>= (spnHora1.getValue()*100+ spnMinutos1.getValue())){
                            salasAMostrar.add(sala);
                            break;}  
                    }
                }
            }
    }

    public void deshabilitarDatos(){
        txtCapacidad.setDisable(true);
        cBoxOrganizador.setDisable(true);
        txtRecurso.setDisable(true);
        datePicker.setDisable(true);
        spnHora.setDisable(true);
        spnMinutos.setDisable(true);
        spnHora1.setDisable(true);
        spnMinutos1.setDisable(true);
        cBoxSala.setDisable(true);
        butIntroducirDatos.setDisable(true);
        butCambiarDatos.setDisable(false);
    }

    public void cambiarDatos(){
        txtCapacidad.setDisable(false);
        cBoxOrganizador.setDisable(false);
        txtRecurso.setDisable(false);
        datePicker.setDisable(false);
        spnHora.setDisable(false);
        spnMinutos.setDisable(false);
        spnHora1.setDisable(false);
        spnMinutos1.setDisable(false);
        butIntroducirDatos.setDisable(false);
        txtNombre.setDisable(true);
        txtAsunto.setDisable(true);
        txtCorreo.setDisable(true);
        butAñadirParticipantes.setDisable(true);
        butCrearReserva.setDisable(true);
        butCambiarSala.setDisable(true);
        txtAsunto.clear();
        txtNombre.clear();
        txtCorreo.clear();
        participantes.clear();
        refrescarListaP();
        labIdSala.setText("ID: ");
    }
    
    public void cambiarSala(){
        cBoxSala.setDisable(false);
        txtNombre.setDisable(true);
        txtCorreo.setDisable(true);
        butAñadirParticipantes.setDisable(true);
        participantes.clear();
        refrescarListaP();
        txtNombre.clear();
        txtCorreo.clear();  
    }
    
    public void validarCantidadParticipantes(){
        
    }
    
    public void validarChoqueReservas(){
        
    }
    
    public void crearReserva() throws Exception{
        //Falta escribir las dos validaciones anteriores aqui.
        String salaId="";
        int estudianteId=0;
        for (Sala sala: salas.getSalasActivas()){
            if (cBoxSala.getSelectionModel().getSelectedItem()==sala){
                salaId= sala.getId();
                break;
            }
        }
        for (Estudiante estudiante: estudiantes.getLista()){
            if (cBoxOrganizador.getSelectionModel().getSelectedItem()==estudiante){
                estudianteId= estudiante.getCarnet();
                break;
            }
        }
        //System.out.println(salaId);
        Reserva nueva = new Reserva(txtAsunto.getText(), estudianteId, salaId, "Activa", participantes, datePicker.getValue(), LocalTime.of(spnHora.getValue(), spnMinutos.getValue()), LocalTime.of(spnHora1.getValue(), spnMinutos1.getValue()) );
        reservas.getLista().add(nueva);
        reservas.saveInXML();
        participantes.clear();
    }
    
    public boolean validarExistenciaReservas(){
        //Hay que terminarla...
        String salaId="";
        for (Sala sala: salas.getSalasActivas()){
            if (cBoxSala.getSelectionModel().getSelectedItem()==sala){
                salaId= sala.getId();
                break;
            }
        }
        ArrayList<Reserva> reservas_salas= reservas.reservasSala(salaId);
        for (Reserva res: reservas_salas){
            if (res.getFecha()==datePicker.getValue());
                    //&& (res.getHoraInicio()).compareTo(LocalTime.MIN)){
                ;
            }
    return true;}
    
}


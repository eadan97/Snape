package Controllers;

import Models.*;
import Models.Wrappers.Estudiantes;
import Models.Wrappers.Reservas;
import Models.Wrappers.Salas;
import Utils.SMSHandler;
import Utils.Utils;
import Utils.correojava.Correo;
import Views.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SalaReservarController {
    public TableView tblParticipantes;
    public TableColumn tbcNombre;
    public TableColumn tbcCorreo;
    public TextField txtAsunto;
    public ComboBox cBoxSala;
    public ComboBox cBoxOrganizador;
    public DatePicker datePicker;
    public Spinner<Integer> spnHoraI = new Spinner<>();
    public Spinner<Integer> spnMinutosI = new Spinner<>();
    public Spinner<Integer> spnHoraF = new Spinner<>();
    public Spinner<Integer> spnMinutosF = new Spinner<>();
    public TextField txtNombre;
    public TextField txtCorreo;
    public TextField txtCapacidad;
    public TextField txtRecurso;
    public Label labIdSala;
    public Button btnAñadirParticipantes;
    public Button btnCrearReserva;
    public Button btnIntroducirDatos;
    public Button btnCambiarDatos;
    public Button btnCambiarSala;

    ArrayList<Sala> salasAMostrar= new ArrayList<>();
    ArrayList<Participante> participantes= new ArrayList<>();
    Reservas reservas = Main.getInstance().reservas;
    Salas salas = Main.getInstance().salas;
    Estudiantes estudiantes= Main.getInstance().estudiantes;

    public void initialize(){
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        //poblarCBoxSalas();
        poblarEstudiantes();
        alCambiarDatos();
        //refrescarLista();
        //validarFecha(datePicker);
    }

    /**
     * Metodo para poblar con estudiantes validos.
     * TODO: Arreglar metodos para que funcionen.
     */
    private void poblarEstudiantes() {
        for (Estudiante estudiante: estudiantes.getLista()) {
            if (estudiante.getCalificacion()>70
                    &&reservas.cantidadIncidenciasEstudiante(estudiante)<5
                    &&reservas.reservasSemanalesEstudiante(estudiante)<=3)
                cBoxOrganizador.getItems().add(estudiante);
        }
    }

    /**
     * Pobla el cBoxSala.
     */
    private void poblarCBoxSalas(){
        for (Sala sala: salasAMostrar){
            cBoxSala.getItems().add(sala);
        }
    }

    /**
     * Metodo para refrescar la lista de participantes.
     */
    private void refrescarLista() {
        ObservableList<Participante> list = FXCollections.observableArrayList(participantes);
        tblParticipantes.setItems(list);
    }

    /**
     * Metodo que se llama cuando le da click a "Validar Datos"
     * @param actionEvent
     */
    public void alIngresarDatos(ActionEvent actionEvent) {
        if (!validarCamposObligatorios()){
            Utils.mostrarError("Error","Campos obligatorios sin información", "Debe llenar los campos necesarios antes de realizar la reserva.");
            return;}
        if (!validarFecha()){
            Utils.mostrarError("Error","Error en los datos ingresados","La fecha ya transcurrió.");
            return;}
        if (!validarHoraInicioFin()){
            Utils.mostrarError("Error","Error en los datos ingresados","La hora de inicio debe ser menor a la de finalización.");
            return;}



        cBoxSala.setDisable(false);
        cBoxSala.getItems().clear();
        salasAMostrar.clear();
        labIdSala.setText("ID: ");
        //participantes.clear();
        discriminarSalas();
        poblarCBoxSalas();
        deshabilitarDatos();
        //cBoxSala.setDisable(false);
    }

    public void agregarParticipante(ActionEvent actionEvent) {
        if (!Utils.validarCorreo(txtCorreo.getText())
                ||txtNombre.getText().isEmpty()){
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
            return;
        }
        participantes.add(new Participante(txtNombre.getText(),txtCorreo.getText()));
        txtNombre.clear();
        txtCorreo.clear();
        refrescarLista();
    }



    /**
     * Valida la fecha del DatePicker.
     * @return Boolean: Es valido?
     */
    public boolean validarFecha(){
        LocalDate fecha = datePicker.getValue();
        return fecha.compareTo(LocalDate.now())>=0;
    }

    /**
     * Valida las entradas de las horas.
     * @return Son validas?
     */
    public boolean validarHoraInicioFin(){

        LocalTime horaInicio=LocalTime.of(spnHoraI.getValue(), spnMinutosI.getValue());
        LocalTime horaFin=LocalTime.of(spnHoraF.getValue(), spnMinutosF.getValue());

        if (datePicker.getValue().isEqual(LocalDate.now())&&LocalTime.now().isBefore(horaInicio)&&horaInicio.isBefore(horaFin))
                return true;
        else if(horaInicio.isBefore(horaFin))
            return true;
        return false;
    }

    /**
     * Metodo para validar los campos obligatorios.
     * @return Boolean: Son validos?
     */
    public boolean validarCamposObligatorios(){
        return Utils.validarNumero(txtCapacidad.getText())
                && datePicker.getValue() != null
                && cBoxOrganizador.getSelectionModel().getSelectedItem() != null;
    }

    public void enSalaEscogida(ActionEvent actionEvent){
        //deshabilitarDatos();
        txtNombre.setDisable(false);
        txtCorreo.setDisable(false);
        btnAñadirParticipantes.setDisable(false);
        txtAsunto.setDisable(false);
        btnCrearReserva.setDisable(false);
        cBoxSala.setDisable(true);
        btnCambiarSala.setDisable(false);
        labIdSala.setText("ID: "+cBoxSala.getSelectionModel().getSelectedItem());
    }

    /**
     * Busca las salas que cumplen con los datos.
     */
    public void discriminarSalas(){
        for (Sala sala: salas.getSalasActivas()) {
            if (sala.getCapacidadMaxima()>=Integer.parseInt(txtCapacidad.getText())
                    && (sala.getRecursos().toLowerCase().contains(txtRecurso.getText().toLowerCase()) || txtRecurso.getText()==null)){
                for (Horario horario: sala.getAgendaServicio()){
                    if(horario.getDia()==datePicker.getValue().getDayOfWeek()
                            && (horario.getInicio().getHour()*100+ horario.getInicio().getMinute())<= (spnHoraI.getValue()*100+ spnMinutosI.getValue())
                            && (horario.getFin().getHour()*100+ horario.getFin().getMinute())>= (spnHoraF.getValue()*100+ spnMinutosF.getValue())){
                        salasAMostrar.add(sala);
                        for (Reserva reserva:reservas.reservasSala(sala.getId())){
                            if  (reserva.getFecha().isEqual(datePicker.getValue())
                                    && (Utils.estaEnMedio(reserva.getHoraInicio(),LocalTime.of(spnHoraI.getValue(),spnMinutosI.getValue()),reserva.getHoraFin())
                                        ||Utils.estaEnMedio(reserva.getHoraInicio(),LocalTime.of(spnHoraF.getValue(),spnMinutosF.getValue()),reserva.getHoraFin()))){
                                salasAMostrar.remove(sala);
                                break;
                            }
                        }
                        break;}
                }
            }
        }

    }

    /**
     * Desabilita los datos.
     */
    public void deshabilitarDatos(){
        cBoxOrganizador.setDisable(true);
        txtCapacidad.setDisable(true);
        txtRecurso.setDisable(true);
        datePicker.setDisable(true);
        spnHoraI.setDisable(true);
        spnMinutosI.setDisable(true);
        spnHoraF.setDisable(true);
        spnMinutosF.setDisable(true);
        btnIntroducirDatos.setDisable(true);

        cBoxSala.setDisable(false);
        btnCambiarDatos.setDisable(false);

        txtNombre.setDisable(true);
        txtCorreo.setDisable(true);
        btnAñadirParticipantes.setDisable(true);
    }

    public void alCambiarDatos(){
        //participantes=new ArrayList<>();
        txtCapacidad.setDisable(false);
        cBoxOrganizador.setDisable(false);
        txtRecurso.setDisable(false);
        datePicker.setDisable(false);
        spnHoraI.setDisable(false);
        spnMinutosI.setDisable(false);
        spnHoraF.setDisable(false);
        spnMinutosF.setDisable(false);
        btnIntroducirDatos.setDisable(false);
        txtNombre.setDisable(true);
        txtAsunto.setDisable(true);
        txtCorreo.setDisable(true);
        btnAñadirParticipantes.setDisable(true);
        btnCrearReserva.setDisable(true);
        btnCambiarSala.setDisable(true);
        txtAsunto.clear();
        txtNombre.clear();
        txtCorreo.clear();
        participantes.clear();
        refrescarLista();
        labIdSala.setText("ID: ");
    }

    public void alCambiarSala(){
        /*cBoxSala.setDisable(false);
        txtNombre.setDisable(true);
        txtCorreo.setDisable(true);
        btnAñadirParticipantes.setDisable(true);*/
        deshabilitarDatos();
        participantes.clear();
        refrescarLista();
        txtNombre.clear();
        txtCorreo.clear();
    }

    public boolean validarCantidadParticipantes(){
        return (Integer.parseInt(txtCapacidad.getText())<=(participantes.size()+1)&&
                (participantes.size()+1)<=((Sala)cBoxSala.getSelectionModel().getSelectedItem()).getCapacidadMaxima());
    }



    public void crearReserva() throws Exception{
        if (!validarCantidadParticipantes()){
            Utils.mostrarError("Error","Error en los datos ingresados","Revise la cantidad de participantes ingresados!");
            return;
        }
        if ("".equals(txtAsunto.getText())){
            Utils.mostrarError("Error","Error en los datos ingresados","Tiene que ingresar el asunto!");
            return;
        }

        Reserva nueva = new Reserva(
                txtAsunto.getText(),
                ((Estudiante)cBoxOrganizador.getSelectionModel().getSelectedItem()).getCarnet(),
                ((Sala)cBoxSala.getSelectionModel().getSelectedItem()).getId(),
                "Activa",
                participantes,
                datePicker.getValue(),
                LocalTime.of(spnHoraI.getValue(), spnMinutosI.getValue()),
                LocalTime.of(spnHoraF.getValue(), spnMinutosF.getValue()) );
        reservas.agregar(nueva);
        try {
            SMSHandler.enviarCodigo((((Estudiante) cBoxOrganizador.getSelectionModel().getSelectedItem()).getTelefono()),nueva.getIdSala()+"-"+nueva.getId()+"-"+nueva.getIdOrganizador());
        }catch (Exception e){e.printStackTrace();}
        
        //enviar el correo!
        //mensaje del correo
        String msg_correo="Sala: "+nueva.getIdSala()+"\nUbicación: "+((Sala)cBoxSala.getSelectionModel().getSelectedItem()).getUbicacion()+"\nFecha: "+datePicker.getValue().toString()+"\nHora de inicio: "+LocalTime.of(spnHoraI.getValue(), spnMinutosI.getValue()).toString()+"\nHora de finalización: "+LocalTime.of(spnHoraF.getValue(), spnMinutosF.getValue()).toString();
        String destinatarios="";
        for (Participante participante: participantes){
            destinatarios+=","+participante.getCorreo();
        }
        if (!"".equals(destinatarios)){
            destinatarios=destinatarios.substring(1);
            Correo correo= new Correo(msg_correo, destinatarios);
            correo.enviarCorreo();}
        
        
        Correo correo_org= new Correo(msg_correo+"\nCódigo de calificación: "+nueva.getIdSala()+"-"+nueva.getId()+"-"+nueva.getIdOrganizador(), (((Estudiante) cBoxOrganizador.getSelectionModel().getSelectedItem()).getCorreo()));
        correo_org.enviarCorreo();
        
        //correo a enviar
        //Correo correo= new Correo();
        //correo.enviarCorreo();
        //Correo correo= new Correo(msg_correo, destinatarios);
        //correo.enviarCorreo();
        
        
        reservas.saveInXML();
        alCambiarDatos();
        participantes.clear();
    }



}


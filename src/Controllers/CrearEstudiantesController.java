package Controllers;

import Models.Estudiante;
import Models.Wrappers.Estudiantes;
import Utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Clase para controlar la ventana para crear estudiantres.
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class CrearEstudiantesController extends MenuBarController {

    Estudiantes estudiantes=new Estudiantes();

    public TableView<Estudiante> tblEstudiantes;
    public TableColumn tbcCarnet;
    public TableColumn tbcNombre;
    public TableColumn tbcCarrera;
    public TableColumn tbcCorreo;
    public TableColumn tbcTelefono;
    public TableColumn tbcCalificacion;
    public TextField txtNombre;
    public TextField txtCarnet;
    public TextField txtCorreo;
    public TextField txtTelefono;
    public TextField txtCarrera;

    /**
     * Metodo de inicio.
     */
    public void initialize(){
        //Se le dice a cada TableColumn que dato va a recibir de la lista
        tbcCarnet.setCellValueFactory(new PropertyValueFactory<>("carnet"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        tbcCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbcCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));

        //se refresca la lista
        refrescarLista();


    }

    /**
     * Metodo para refrecar el TableView con los estudiantes
     */
    public void refrescarLista(){
        ObservableList<Estudiante> list = FXCollections.observableArrayList(estudiantes.getLista());
        tblEstudiantes.setItems(list);
    }

    /**
     * Metodo que se llama para agregar Estudiante
     * @param actionEvent Boton precionado
     * @throws Exception
     */
    public void agregarEstudiante(ActionEvent actionEvent) throws Exception {
        if (!Utils.validarCorreo(txtCorreo.getText())
                ||!Utils.validarNumero(txtTelefono.getText())
                ||!Utils.validarNumero(txtCarnet.getText())
                ||estudiantes.verificarCarnet(Integer.parseInt(txtCarnet.getText()))
                || txtCarrera.getText().isEmpty()
                ||txtNombre.getText().isEmpty()){
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
            return;
        }

        Estudiante nuevo = new Estudiante(txtNombre.getText(),Integer.parseInt(txtCarnet.getText()), txtCarrera.getText(),txtCorreo.getText(),Integer.parseInt(txtTelefono.getText()));

        txtNombre.setText("");
        txtCarnet.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtCarrera.setText("");

        estudiantes.add(nuevo);
        estudiantes.saveInXML();
        refrescarLista();
    }



}

package Controllers;

import Models.Sala;
import Models.Wrappers.Salas;
import Utils.Utils;
import Views.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SalasController {
    public TableColumn tbcId;
    public TableColumn tbcUbicacion;
    public TableColumn tbcCapacidad;
    public TableColumn tbcRecursos;
    public TableColumn tbcEstado;
    public TableColumn tbcCalificacion;
    public TableView<Sala> tblSalas;
    public TextField txtUbicacion;
    public TextField txtCapacidad;
    public TextField txtRecursos;
    public static Sala sala;
    Salas salas = Main.getInstance().salas;


    public void initialize(){

        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        tbcCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadMaxima"));
        tbcRecursos.setCellValueFactory(new PropertyValueFactory<>("recursos"));
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tbcCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));

        tbcUbicacion.setCellFactory(TextFieldTableCell.forTableColumn());
        tbcEstado.setCellFactory(TextFieldTableCell.forTableColumn());
        tbcRecursos.setCellFactory(TextFieldTableCell.forTableColumn());
        refrescarLista();
    }
    public void agregarSala(ActionEvent actionEvent) throws Exception {

        if (!Utils.validarNumero(txtCapacidad.getText())
                ||txtUbicacion.getText().isEmpty()
                ||txtRecursos.getText().isEmpty()){
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
            return;
        }
        Sala nueva=new Sala(txtUbicacion.getText(),Integer.parseInt(txtCapacidad.getText()),txtRecursos.getText());
        sala=nueva;
        obtenerHorario();
        txtUbicacion.setText("");
        txtCapacidad.setText("");
        txtRecursos.setText("");

        salas.add(nueva);
        salas.saveInXML();
        refrescarLista();

    }


    public void refrescarLista(){
        ObservableList<Sala> list = FXCollections.observableArrayList(salas.getLista());
        tblSalas.setItems(list);
    }


    public void obtenerHorario() throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/JavaFX/horario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Horario");
            stage.setScene(new Scene(root, 800, 500));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editarUbicacion(TableColumn.CellEditEvent actionEvent) throws Exception {
        for (Sala sala:
             salas.getLista()) {
            if(sala.getId().equals(((Sala) actionEvent.getRowValue()).getId()))
                sala.setUbicacion(actionEvent.getNewValue().toString());
        }
        salas.saveInXML();
    }

    public void editarEstado(TableColumn.CellEditEvent cellEditEvent) throws Exception{
        if (cellEditEvent.getNewValue().toString().equals("Activa")
                ||cellEditEvent.getNewValue().toString().equals("Inactiva")
                ||cellEditEvent.getNewValue().toString().equals("Mantenimiento")){
            for (Sala sala:
                    salas.getLista()) {
                if(sala.getId().equals(((Sala) cellEditEvent.getRowValue()).getId()))
                    sala.setEstado(cellEditEvent.getNewValue().toString());
            }
            salas.saveInXML();
        }else{
            cellEditEvent.getTableView().refresh();
        }
    }

    public void editarRecursos(TableColumn.CellEditEvent cellEditEvent) throws Exception{
        for (Sala sala:
                salas.getLista()) {
            if(sala.getId().equals(((Sala) cellEditEvent.getRowValue()).getId()))
                sala.setRecursos(cellEditEvent.getNewValue().toString());
        }
        salas.saveInXML();
    }

}

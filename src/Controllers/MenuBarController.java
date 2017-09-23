package Controllers;


import Views.Main;
import javafx.event.ActionEvent;

public class MenuBarController {
    public void menuAdministrarEstudiantes(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/estudiantes.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void menuAdministrarSalas(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/salas.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void menuCalificarSala(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/calificarSala.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void menuReservarSala(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/reservarSala.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

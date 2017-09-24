package Controllers;


import Views.Main;
import javafx.event.ActionEvent;

public class MenuBarController {
    public void menuAdministrarEstudiantes(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/crearEstudiantes.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void menuAdministrarSalas(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/crearSalas.fxml");

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

    public void menuConsultarSala(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/consultarSala.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void menuConsultarEstudiante(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/consultarEstudiante.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

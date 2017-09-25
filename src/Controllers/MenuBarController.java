package Controllers;


import Views.Main;
import javafx.event.ActionEvent;

/**
 * Metodo que controla la barra de navegacion.
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
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

    public void menuTopSalaUso(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/topSalaUso.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void menuTopHorarioUso(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/topHorarioUso.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void menuTopCarreraUso(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/topCarreraUso.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void menuTopSalaCalificacion(ActionEvent actionEvent) {
        try {
            Main.getInstance().replaceSceneContent("/Views/JavaFX/topSalaCalificacion.fxml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

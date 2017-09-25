package Views;

import Models.Wrappers.Estudiantes;
import Models.Wrappers.Horarios;
import Models.Wrappers.Reservas;
import Models.Wrappers.Salas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class Main extends Application {

    //TODO: Precargar 10 estudiantes en el XML
    //TODO: Precargar 6 horarios
    public Estudiantes estudiantes;
    public Salas salas;
    public Horarios horarios;
    public Reservas reservas;
    Stage stage;

    private static Main instance;

    /**
     * Constructor por defecto.
     */
    public Main () {
        instance=this;
    }

    /**
     * Metodo que devuelve la instancia de main.
     * @return instancia del main.
     */
    public static Main getInstance(){
        return instance;
    }

    /**
     * Metodo principal
     * @param args Argumentos
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        launch(args);
    }

    /**
     * Metodo que empieza la ejecucion de la aplicacion de JAVA FX.
     * @param primaryStage Stage principal.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        estudiantes=new Estudiantes();
        salas=new Salas();
        horarios=new Horarios();
        reservas =new Reservas();

        estudiantes.loadFromXML();
        salas.loadFromXML();
        horarios.loadFromXML();
        reservas.loadFromXML();

        stage=primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("/Views/JavaFX/main.fxml"));
        primaryStage.setTitle("Sistema SNAPE");
        primaryStage.setScene(new Scene(root, 800,500));
        primaryStage.show();
    }

    /**
     * Metodo para remplazar el FXML actual.
     * @param fxml Nuevo FXML.
     * @return Padre
     * @throws Exception
     */
    public Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 800, 500);
            //scene.getStylesheets().agregar(getClass().getResource("demo.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }


}//( ͡° ͜ʖ ͡°)

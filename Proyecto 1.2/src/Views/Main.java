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

public class Main extends Application {

    //TODO: Precargar 10 estudiantes en el XML
    //TODO: Precargar 6 horarios
    //TODO: JAVADOC EVERYWHERE
    public Estudiantes estudiantes;
    public Salas salas;
    public Horarios horarios;
    public Reservas reservas;
    Stage stage;

    private static Main instance;

    public Main () {
        instance=this;
    }
    public static Main getInstance(){
        return instance;
    }

    public static void main(String[] args) throws Exception{
        //estudiantes.add(new Estudiante("asd",123456,"carrera","correo",12345));
        launch(args);


    }

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

    public Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 800, 500);
            //scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }






}

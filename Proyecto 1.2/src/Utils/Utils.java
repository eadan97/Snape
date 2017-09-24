package Utils;

import javafx.scene.control.Alert;

import java.time.LocalTime;

public class Utils {
    public static boolean validarNumero(String numero){
        return numero.matches("\\d+");
    }
    public static boolean validarCorreo(String correo){
        return correo.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
    }
    public static void mostrarError(String titulo, String cabecera, String cuerpo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(cuerpo);

        alert.showAndWait();
    }

    /**
     * Metodo que revisa si la hora del medio esta entre inicio y fin
     * @param inicio
     * @param medio
     * @param fin
     * @return
     */
    public static boolean estaEnMedio(LocalTime inicio, LocalTime medio, LocalTime fin){
        return medio.compareTo(inicio) >= 0 && medio.compareTo(fin) <= 0;
    }
    public static boolean numeroMayorQue(String string, int numero){
        if (validarNumero(string))
            return Integer.parseInt(string)>numero;
        else
            return false;
    }
    public static boolean numeroMenorQue(String string, int numero){
        if (validarNumero(string))
            return Integer.parseInt(string)<numero;
        else
            return false;
    }

    public static boolean validarCodigoFormato(String text) {
        return text.matches("SAL-\\d+-\\d+-\\d+");
    }
}

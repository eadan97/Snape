package Utils;

import Models.Correo;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase con metodos utiles.
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class Utils {
    /**
     * Metodo que valida si un String es un numero.
     *
     * @param numero String a validar.
     * @return Boolean:El String es un numero?
     */
    public static boolean validarNumero(String numero) {
        return numero.matches("\\d+");
    }

    /**
     * Metodo que valida si un String es un correo.
     *
     * @param correo String a validar.
     * @return Boolean:El String es un correo?
     */
    public static boolean validarCorreo(String correo) {
        return correo.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
    }

    /**
     * Metodo que muestra un error en pantalla.
     *
     * @param titulo   Titulo del error.
     * @param cabecera Cabecera del error.
     * @param cuerpo   Cuerpo del error.
     */
    public static void mostrarError(String titulo, String cabecera, String cuerpo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(cuerpo);

        alert.showAndWait();
    }

    /**
     * Metodo que revisa si la hora del medio esta entre inicio y fin
     *
     * @param inicio Hora de inicio.
     * @param medio Hora del medio.
     * @param fin Hora final.
     * @return Boolean: medio esta en el medio?
     */
    public static boolean estaEnMedio(LocalTime inicio, LocalTime medio, LocalTime fin) {
        return medio.compareTo(inicio) >= 0 && medio.compareTo(fin) <= 0;
    }

    /**
     * Metodo que valida si un String es un numero y ademas, si este es mayor que un numero.
     *
     * @param string String a validar.
     * @param numero Numero.
     * @return Boolean:El String es un numero mayor que el numero?
     */
    public static boolean numeroMayorQue(String string, int numero) {
        if (validarNumero(string))
            return Integer.parseInt(string) > numero;
        else
            return false;
    }

    /**
     * Metodo que valida si un String es un numero y ademas, si este es menor que un numero.
     *
     * @param string String a validar.
     * @param numero Numero.
     * @return Boolean:El String es un numero menor que el numero?
     */
    public static boolean numeroMenorQue(String string, int numero) {
        if (validarNumero(string))
            return Integer.parseInt(string) < numero;
        else
            return false;
    }

    /**
     * Metodo que valida si el String tiene el formato de un codigo para calificar.
     *
     * @param codigo String a validar.
     * @return Boolean: Es valido?
     */
    public static boolean validarCodigoFormato(String codigo) {
        return codigo.matches("SAL-\\d+-\\d+-\\d+");
    }


    /**
     * Metodo para ordenar un Map por sus Values.
     * @param map Mapa.
     * @param <K> Tipo de Keys.
     * @param <V> Tipo de Values.
     * @return Mapa ordenado.
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    /**
     * Metodo para poblar un BarChart.
     * @param grpGrafico BarChart.
     * @param iterator Iterador.
     * @param barra Nombre de la barra.
     */
    public static void poblarChart(BarChart grpGrafico, Iterator iterator, String barra){
        int i=0;
        while (iterator.hasNext()&&i<6){
            Map.Entry entry = (Map.Entry) iterator.next();
            i++;

            XYChart.Series series = new XYChart.Series<>();
            series.setName(entry.getKey().toString());
            series.getData().add(new XYChart.Data<>(barra, entry.getValue()));

            grpGrafico.getData().add(series);
        }
    }


}

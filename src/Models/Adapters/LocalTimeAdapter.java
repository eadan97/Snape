package Models.Adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;

/**
 * Adaptador de String-LocalTime requerido para el JAXB
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class LocalTimeAdapter extends XmlAdapter<String,LocalTime> {
    /**
     * Metodo para convertir de String a LocalTime.
     * @param v String a convertir.
     * @return LocalTime.
     * @throws Exception
     */
    @Override
    public LocalTime unmarshal(String v) throws Exception {
        return LocalTime.parse(v);
    }

    /**
     * Metodo para convertir de LocalTime a String.
     * @param v LocalTime a convertir.
     * @return String.
     * @throws Exception
     */
    @Override
    public String marshal(LocalTime v) throws Exception {
        return v.toString();
    }
}

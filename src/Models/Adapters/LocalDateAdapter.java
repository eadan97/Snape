package Models.Adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Adaptador de String<->LocalDate para JAXB
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class LocalDateAdapter extends XmlAdapter<String,LocalDate> {
    /**
     * Metodo para pasar de String a LocalDate
     * @param v String a convertir
     * @return LocalDate
     * @throws Exception
     */
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    /**
     * Metodo para pasar de LocalDate a String
     * @param v LocalDate a convertir.
     * @return String
     * @throws Exception
     */
    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}

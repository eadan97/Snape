package Models.Adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;

/**
 * Adaptador de String<->LocalTime
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class LocalTimeAdapter extends XmlAdapter<String,LocalTime> {
    @Override
    public LocalTime unmarshal(String v) throws Exception {
        return LocalTime.parse(v);
    }

    @Override
    public String marshal(LocalTime v) throws Exception {
        return v.toString();
    }
}

package Models;

import Models.Adapters.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Clase de horario.
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
@XmlType(propOrder = {"dia","inicio","fin"})
@XmlRootElement(name = "horario")
public class Horario {
    private DayOfWeek dia;
    private LocalTime inicio;
    private LocalTime fin;

    public Horario() {}

    public Horario(DayOfWeek dia, LocalTime inicio, LocalTime fin) {
        this.dia = dia;
        this.inicio = inicio;
        this.fin = fin;
    }

    @XmlElement
    public DayOfWeek getDia() {
        return dia;
    }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return  dia.getDisplayName(TextStyle.FULL,new Locale("es", "ES")) +
                ": " + inicio +
                "-" + fin;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Horario){
            Horario objHorario=(Horario)obj;
            return objHorario.dia==this.dia
                    &&objHorario.inicio==this.inicio
                    &&objHorario.fin==this.fin;
        }
        return false;
    }
}

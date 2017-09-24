package Models;

import Models.Adapters.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.DayOfWeek;
import java.time.LocalTime;

@XmlType(propOrder = {"dia","inicio","fin"})

@XmlRootElement(name = "turno")
public class Turno {
    private DayOfWeek dia;
    private LocalTime inicio;
    private LocalTime fin;

    public Turno() {}

    public Turno(DayOfWeek dia, LocalTime inicio, LocalTime fin) {
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

}

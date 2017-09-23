package Models;

import Models.Adapters.LocalDateAdapter;
import Models.Adapters.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@XmlType(propOrder = {"asunto", "idOrganizador","idSala","estado","fecha","horaInicio","horaFin","participantes","idReserva"})
@XmlRootElement(name = "reserva")
public class Reserva {
    String asunto;
    String idOrganizador;
    String idSala;
    String estado;
    List<Participante> participantes;
    LocalDate fecha;
    LocalTime horaInicio;
    LocalTime horaFin;
    static String idReserva;

    public Reserva() {
    }

    //TODO: Revisar si la fecha esta bien y/o exportarla a otra clase


    public Reserva(String asunto, String idOrganizador,String idSala, String estado, List<Participante> participantes, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.asunto = asunto;
        this.idOrganizador= idOrganizador;
        this.idSala = idSala;
        this.estado = estado;
        this.participantes = participantes;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    @XmlElement
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    @XmlElement
    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    @XmlElement
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlElement
    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    @XmlElement
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public String getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(String idOrganizador) {
        this.idOrganizador = idOrganizador;
    }
    
    
}

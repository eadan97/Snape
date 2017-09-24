package Models;

import Models.Adapters.LocalDateAdapter;
import Models.Adapters.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Clase de reserva.
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
@XmlType(propOrder = {"id","idSala","idOrganizador","asunto","estado","fecha","horaInicio","horaFin","participantes"})
@XmlRootElement(name = "reserva")
public class Reserva {
    static int cantReservas=0;
    int id;
    String asunto;
    String idSala;
    int idOrganizador;
    String estado;
    ArrayList<Participante> participantes;
    LocalDate fecha;
    LocalTime horaInicio;
    LocalTime horaFin;

    public Reserva() {
    }

    //TODO: Revisar si la fecha esta bien y/o exportarla a otra clase


    public Reserva(String asunto, int idOrganizador, String idSala, String estado, ArrayList<Participante> participantes, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        cantReservas++;
        this.id=cantReservas;
        this.idOrganizador=idOrganizador;
        this.asunto = asunto;
        this.idSala = idSala;
        this.estado = estado;
        this.participantes = new ArrayList<>(participantes);
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    @XmlElement
    public int getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(int pIdOrganizador) {
        idOrganizador = pIdOrganizador;
    }

    public static int getCantReservas() {
        return cantReservas;
    }

    public static void setCantReservas(int pCantReservas) {
        cantReservas = pCantReservas;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int pId) {
        id = pId;
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
    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
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

    /**
     * Retorna la cantidad de participantes.
     * @return Cantidad de participantes.
     */
    public int getCantidadParticipantes(){
        return participantes.size();
    }
}

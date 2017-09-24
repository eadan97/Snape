package Models;

import Models.Adapters.LocalDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Clase de incidente.
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
@XmlRootElement(name = "incidente")
public class Incidente {
    private String tipo;
    private String idSala;
    private LocalDate fecha;
    private String detalle;

    public Incidente() {
    }

    public Incidente(String tipo, String idSala, LocalDate fecha, String detalle) {
        this.tipo = tipo;
        this.idSala = idSala;
        this.fecha = fecha;
        this.detalle = detalle;
    }
    @XmlElement
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String pTipo) {
        tipo = pTipo;
    }

    @XmlElement
    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String pIdSala) {
        idSala = pIdSala;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate pFecha) {
        fecha = pFecha;
    }

    @XmlElement
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String pDetalle) {
        detalle = pDetalle;
    }
}

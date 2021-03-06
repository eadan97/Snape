package Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase de participante.
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
@XmlType(propOrder = {"nombre","correo"})
@XmlRootElement(name = "participante")
public class Participante {
    private String nombre;
    private String correo;

    /**
     * Constructor por defecto.
     */
    public Participante() {
    }

    /**
     * Constructor sobrecargado.
     * @param nombre Nombre.
     * @param correo Correo.
     */
    public Participante(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
}

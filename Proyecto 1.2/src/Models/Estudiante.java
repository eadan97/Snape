package Models;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase de estudiantes
 * @author Esteban Esquivel
 * @author Israel Padilla
 * @author Israel
 */
@XmlType(propOrder = {"nombre","carrera","correo","telefono","calificacion", "incidentes"})
@XmlRootElement(name = "estudiante")
public class Estudiante {
    private String nombre;
    private int carnet;
    private String carrera;
    private String correo;
    private int telefono;
    private int calificacion=100;
    private ArrayList<Incidente> incidentes=new ArrayList<>();

    public Estudiante(){}

    public Estudiante(String nombre, int carnet, String carrera, String correo, int telefono) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.carrera = carrera;
        this.correo = correo;
        this.telefono = telefono;
    }

    @XmlElement
    public ArrayList<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(ArrayList<Incidente> pIncidentes) {
        incidentes = pIncidentes;
    }

    public void agregarIncidencia(String tipo, String idSala, LocalDate fecha, String detalle){
        if (tipo.equals("Sala reservada no utilizada")
                ||tipo.equals("Ruido excesivo")
                ||tipo.equals("Basura y desorden")){
            //TODO TERMINAR INCIDENCIA Y EXCEPCIONES.

        }
    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlAttribute
    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }
    @XmlElement
    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    @XmlElement
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @XmlElement
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    @XmlElement
    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return nombre + " - " + carnet;
    }
}

package Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

/**
 * Clase de Sala.
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
@XmlType(propOrder = {"id","ubicacion","capacidadMaxima","recursos","estado", "calificaciones","agendaServicio"})
public class Sala {
    private static int cantidadSalas=0;

    private String id;
    private String ubicacion;
    private int capacidadMaxima;
    private String recursos;
    private String estado="Activa";
    private ArrayList<Calificacion> calificaciones =new ArrayList<>();
    private ArrayList<Horario> agendaServicio=new ArrayList<>();

    /**
     * Constructor por defecto.
     */
    public Sala() {}

    /**
     * Constructor sobrecargado.
     * @param ubicacion Ubicacion.
     * @param capacidadMaxima Capacidad maxima de la sala.
     * @param recursos Recursos.
     */
    public Sala( String ubicacion, int capacidadMaxima, String recursos) {
        cantidadSalas++;
        this.id = "SAL-"+cantidadSalas;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
        this.recursos = recursos;
    }

    /**
     * Metodo para agregar un horario.
     * @param horario Horario a agregar.
     */
    public void agregarHorario(Horario horario){
        if (!validarHorario(horario))
            agendaServicio.add(horario);
    }

    /**
     * Metodo para remover un horario.
     * @param pHorario Horario a remover.
     */
    public void eliminarHorario(Horario pHorario){
                agendaServicio.remove(pHorario);

    }

    /**
     * Metodo para ver si un horario es valido.
     * @param pHorario Horario a validar.
     * @return Boolean: Es valido?
     */
    public boolean validarHorario(Horario pHorario){
        for (Horario horario : agendaServicio) {
            if (horario==pHorario)
                return true;
        }
        return false;
    }
    @XmlElement(name="agendaServicio")
    public ArrayList<Horario> getAgendaServicio() {
        return agendaServicio;
    }

    public void setAgendaServicio(ArrayList<Horario> pAgendaServicio) {
        agendaServicio = pAgendaServicio;
    }


    public static int getCantidadSalas() {
        return cantidadSalas;
    }

    public static void setCantidadSalas(int cantidadSalas) {
        Sala.cantidadSalas = cantidadSalas;
    }

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @XmlElement
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    @XmlElement
    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    @XmlElement
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlElement
    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Metodo para saber el promedio de las calificaciones, o su valor por defecto (100)
     * @return Calificacion
     */
    public int getCalificacion() {
        if (calificaciones.isEmpty())
            return 100;
        else{
            int res=0;
            for (Calificacion calificacion:
                    calificaciones) {
                res+=calificacion.getNota();
            }
            return res/calificaciones.size();
        }

    }

    public void setCalificaciones(ArrayList<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * Metodo para agregar una calificacion.
     * @param nota Nota.
     * @param codigo Codigo.
     */
    public void agregarCalificacion(int nota, String codigo){
        if (!existeCalificacion(codigo))
            calificaciones.add(new Calificacion(nota, codigo));

    }

    /**
     * Metodo para saber si ya existe esta calificacion.
     * @param codigo Codigo de la calificacion.
     * @return Boolean: Existe?
     */
    public boolean existeCalificacion(String codigo) {
        for (Calificacion calificacion:calificaciones) {
            if (codigo.equals(calificacion.getCodigo()))
                return true;
        }
        return false;
    }

    /**
     * Metodo que devuelve Sala ID
     * @return String
     */
    @Override
    public String toString() {
        return "Sala " + id;
    }
}

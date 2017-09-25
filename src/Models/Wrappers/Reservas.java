package Models.Wrappers;

import Models.Estudiante;
import Models.Reserva;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase de reservas.
 * Esta clase sirve de contenedor de las reservas, ademas es necesaria para la JAXB.
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */

@XmlRootElement(name = "reservas")
public class Reservas {

    private static ArrayList<Reserva> lista=new ArrayList<>();

    @XmlElement(name = "reserva")
    public ArrayList<Reserva> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Reserva> lista) {
        Reservas.lista = lista;
    }

    //Terminan los Getters y Setters

    /**
     * Añade un nueva reserva a la lista de reservas.
     * @param nueva Reserva a añadir.
     */
    public void agregar(Reserva nueva){
        //if (!verificarEstudiante(nueva))
            lista.add(nueva);

    }


    /**
     * Guarda en un XML la lista de reservas.
     * @throws Exception
     */
    public void saveInXML() throws Exception {

        JAXBContext contextObj = JAXBContext.newInstance(Reservas.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(this, new File("reservasDB.xml"));

    }

    /**
     * Carga de un XML la lista de reservas.
     * @throws JAXBException
     */
    public void loadFromXML() throws Exception {
        try {

            File file = new File( "reservasDB.xml" );
            JAXBContext jaxbContext = JAXBContext.newInstance( Reservas.class );

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Reservas lista = (Reservas)jaxbUnmarshaller.unmarshal( file );
            setLista(lista.getLista());
            Reserva.setCantReservas(lista.getLista().size());
        }catch (Exception e){
            saveInXML();}
    }

    /**
     * Cantidad de incidencias de un estudiante.
     * TODO:MOVER ESTO A ESTUDIANTE.
     * @param estudiante Estudiante.
     * @return Cantidad de incidencias.
     */
    public int cantidadIncidenciasEstudiante(Estudiante estudiante) {
        //TODO: Terminar esto
        return 0;
    }

    /**
     * Devuelve las reservas semanales de un estudiante.
     * @param estudiante Estudiante a buscar.
     * @return Cantidad de reservas.
     */
    public int reservasSemanalesEstudiante(Estudiante estudiante) {
        int res=0;
        ArrayList<Reserva> salas=new ArrayList<>();
        for (Reserva reserva:lista) {
            if (reserva.getIdOrganizador()==estudiante.getCarnet()
                    &&reserva.getFecha().isBefore(LocalDate.now().with(DayOfWeek.MONDAY).plusWeeks(1))
                    &&reserva.getFecha().isAfter(LocalDate.now().with(DayOfWeek.MONDAY)))
                res++;
        }

        return res;
    }

    /**
     * Retorna las reservas de una sala.
     * @param id Sala a buscar
     * @return Lista de reservas de la sala
     */
    public ArrayList<Reserva> reservasSala(String id) {
        ArrayList<Reserva> res=new ArrayList<>();
        for (Reserva reserva:lista) {
            if (reserva.getIdSala().equals(id))
                res.add(reserva);
        }
        return res;
    }

    /**
     * Retorna las reservas de una sala en una semana adelante.
     * @param id Sala a buscar.
     * @return Lista de reservas
     */
    public ArrayList<Reserva> reservasSalaSemanal(String id) {
        ArrayList<Reserva> res=new ArrayList<>();
        for (Reserva reserva:lista) {
            if (reserva.getIdSala().equals(id)
                    &&reserva.getFecha().isBefore(LocalDate.now().plusWeeks(1))
                    &&reserva.getFecha().isAfter(LocalDate.now()))
                res.add(reserva);
        }
        return res;
    }

    /**
     * Reservas según el organizador
     * @param carnet Carnet del estudiante organizador
     * @return Lista de reservas
     */
    public ArrayList<Reserva> reservasEstudiante(int carnet) {
        ArrayList<Reserva> res=new ArrayList<>();
        for (Reserva reserva:lista) {
            if (reserva.getIdOrganizador()==carnet)
                res.add(reserva);
        }
        return res;
    }
}

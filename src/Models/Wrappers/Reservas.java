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
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase de reservas.
 * Esta clase sirve de contenedor de las reservas, ademas es necesaria para la JABX.
 *
 * @author Esteban Esquivel
 * @author Israel Padilla
 * @author Israel
 */

@XmlRootElement(name = "reservas")
public class Reservas {

    private static ArrayList<Reserva> lista=new ArrayList<>();

    @XmlElement(name = "reserva")
    public ArrayList<Reserva> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Reserva> lista) {
        this.lista = lista;
    }

    //Terminan los Getters y Setters

    /**
     * Añade un nueva a la lista de Reserva.
     * @param nueva Reserva a añadir.
     */
    public void add(Reserva nueva){
        //if (!verificarEstudiante(nueva))
            lista.add(nueva);
    }

    //TODO: Arreglar esto, seria por fecha diferente y sala diferente
    /**
     * Verifica si el participante tiene un CORREO diferente.
     * @param participanteVerificado Estudiante que se busca en la lista.
     * @return
     */
    public boolean verificarEstudiante(Reserva participanteVerificado){
        for (Reserva participanteVerificar:
             getLista()) {
            //if (participanteVerificado.getCorreo()==participanteVerificar.getCorreo())
                return true;
        }
        return false;
    }

    /**
     * Guarda en un XML la lista de estudiantes
     * @throws Exception
     */
    public void saveInXML() throws Exception {

        JAXBContext contextObj = JAXBContext.newInstance(Reservas.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(this, new File("reservasDB.xml"));

    }

    /**
     * Carga de un XML la lista de estudiantes
     * @throws JAXBException
     */
    public void loadFromXML() throws Exception {
        try {

            File file = new File( "reservasDB.xml" );
            JAXBContext jaxbContext = JAXBContext.newInstance( Reservas.class );

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Reservas lista = (Reservas)jaxbUnmarshaller.unmarshal( file );
            setLista(lista.getLista());
        }catch (Exception e){
            saveInXML();}
    }

    public int cantidadIncidenciasEstudiante(Estudiante estudiante) {
        //TODO: Terminar
        return 0;
    }

    public int reservarSemanalesEstudiante(Estudiante estudiante) {
        //TODO: Terminar
        return 0;
    }

    public ArrayList<Reserva> reservasSala(String id) {
        ArrayList<Reserva> res=new ArrayList<>();
        for (Reserva reserva:lista) {
            if (reserva.getIdSala().equals(id))
                res.add(reserva);
        }
        return res;
    }

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

    public ArrayList<Reserva> reservasEstudiante(int carnet) {
        ArrayList<Reserva> res=new ArrayList<>();
        for (Reserva reserva:lista) {
            if (reserva.getIdOrganizador()==carnet)
                res.add(reserva);
        }
        return res;
    }
}

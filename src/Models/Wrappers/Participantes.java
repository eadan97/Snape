package Models.Wrappers;

import Models.Participante;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase de participantes.
 * Esta clase sirve de contenedor de los participantes, ademas es necesaria para la JABX.
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */

@XmlRootElement(name = "participantes")
public class Participantes {

    private static ArrayList<Participante> lista=new ArrayList<>();

    @XmlElement(name = "participante")
    public ArrayList<Participante> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Participante> lista) {
        this.lista = lista;
    }

    //Terminan los Getters y Setters

    /**
     * Añade un participante a la lista de estudiantes.
     * @param participante Estudiante a añadir.
     */
    public void add(Participante participante){
        if (!verificarEstudiante(participante))
            lista.add(participante);
    }

    /**
     * Verifica si el participante tiene un CORREO diferente.
     * @param participanteVerificado Estudiante que se busca en la lista.
     * @return
     */
    public boolean verificarEstudiante(Participante participanteVerificado){
        for (Participante participanteVerificar:
             getLista()) {
            if (participanteVerificado.getCorreo().equals(participanteVerificar.getCorreo()))
                return true;
        }
        return false;
    }

    /**
     * Guarda en un XML la lista de participantes.
     * @throws Exception
     */
    public void saveInXML() throws Exception {

        JAXBContext contextObj = JAXBContext.newInstance(Participantes.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(this, new File("ParticipantesDB.xml"));

    }

    /**
     * Carga de un XML la lista de participantes.
     * @throws JAXBException
     */
    public void loadFromXML() throws JAXBException {
        File file = new File( "ParticipantesDB.xml" );
        JAXBContext jaxbContext = JAXBContext.newInstance( Participantes.class );

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Participantes estudiantes = (Participantes)jaxbUnmarshaller.unmarshal( file );
        setLista(estudiantes.getLista());
    }

}

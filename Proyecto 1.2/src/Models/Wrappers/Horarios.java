package Models.Wrappers;

import Models.Horario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase de horarios.
 * Esta clase sirve de contenedor de los horarios, ademas es necesaria para la JABX.
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */

@XmlRootElement(name = "horarios")
public class Horarios {

    private static ArrayList<Horario> lista=new ArrayList<>();

    @XmlElement(name = "horario")
    public ArrayList<Horario> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Horario> lista) {
        this.lista = lista;
    }

    //Terminan los Getters y Setters



    /**
     * Guarda en un XML la lista de horarios.
     * @throws Exception
     */
    public void saveInXML() throws Exception {

        JAXBContext contextObj = JAXBContext.newInstance(Horarios.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(this, new File("horariosDB.xml"));

    }

    /**
     * Carga de un XML la lista de horarios.
     * @throws JAXBException
     */
    public void loadFromXML() throws Exception {
        try{
        File file = new File( "horariosDB.xml" );
        JAXBContext jaxbContext = JAXBContext.newInstance( Horarios.class );

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Horarios estudiantes = (Horarios)jaxbUnmarshaller.unmarshal( file );
        setLista(estudiantes.getLista());
        }catch (Exception e){
            saveInXML();
        }

    }

}

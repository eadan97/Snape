package Models.Wrappers;

import Models.Estudiante;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase de estudiantes.
 * Esta clase sirve de contenedor de los estudiantes, ademas es necesaria para la JAXB.
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */

@XmlRootElement(name = "estudiantes")
public class Estudiantes {

    private static ArrayList<Estudiante> lista=new ArrayList<>();

    @XmlElement(name = "estudiante")
    public ArrayList<Estudiante> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Estudiante> lista) {
        Estudiantes.lista = lista;
    }

    //Terminan los Getters y Setters

    /**
     * Añade un estudiante a la lista de estudiantes.
     * @param estudiante Estudiante a añadir.
     */
    public void add(Estudiante estudiante){
        if (!verificarCarnet(estudiante.getCarnet()))
            lista.add(estudiante);
    }

    /**
     * Metodo que devuelve el estudiante que tenga el id.
     * @param id Id del estudiante buscado.
     * @return Estudiante o null.
     */
    public Estudiante estudiantePorId(int id){
        for (Estudiante estudiante :
                lista) {
            if (estudiante.getCarnet()==id)
                return estudiante;
        }
        return null;
    }

    /**
     * Verifica si el estudiante se encuentra en la lista.
     * @param estudianteVerificado Carnet del estudiante que se busca en la lista.
     * @return Booleano: Está en la lista?
     */
    public boolean verificarCarnet(int estudianteVerificado){
        for (Estudiante estudianteVerificar:
             getLista()) {
            if (estudianteVerificado==estudianteVerificar.getCarnet())
                return true;
        }
        return false;
    }

    /**
     * Guarda en un XML la lista de estudiantes.
     * @throws Exception
     */
    public void saveInXML() throws Exception {

        JAXBContext contextObj = JAXBContext.newInstance(Estudiantes.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(this, new File("estudiantesDB.xml"));

    }

    /**
     * Carga de un XML la lista de estudiantes.
     * @throws JAXBException
     */
    public void loadFromXML() throws Exception {
        try {
            File file = new File("estudiantesDB.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Estudiantes.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Estudiantes estudiantes = (Estudiantes) jaxbUnmarshaller.unmarshal(file);
            setLista(estudiantes.getLista());
        }catch (Exception e){
            saveInXML();
        }
    }

}

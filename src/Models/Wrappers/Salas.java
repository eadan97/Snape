package Models.Wrappers;

import Models.Sala;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase de salas.
 * Esta clase sirve de contenedor de los participantes, ademas es necesaria para la JABX.
 *
 * @author Esteban Esquivel
 * @author Israel Padilla
 * @author Israel
 */

@XmlRootElement(name = "salas")
public class Salas {

    private static ArrayList<Sala> lista=new ArrayList<>();

    @XmlElement(name = "sala")
    public ArrayList<Sala> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Sala> lista) {
        this.lista = lista;
    }

    //Terminan los Getters y Setters

    /**
     * Añade una sala a la lista de salas.
     * @param nueva Sala a añadir.
     */
    public void add(Sala nueva){
        if (!verificarSala(nueva.getId()))
            lista.add(nueva);
    }

    /**
     * Verifica id de sala existe.
     * @param salaVerificarId Sala que se busca en la lista.
     * @return Boolean: Existe?
     */
    public boolean verificarSala(String salaVerificarId){
        for (Sala sala:
             getLista()) {
            if (salaVerificarId.equals(sala.getId()))
                return true;
        }
        return false;
    }

    /**
     * Devuelve lista de salas activas.
     * @return ArrayList de Salas
     */
    public ArrayList<Sala> getSalasActivas(){
        ArrayList<Sala> res =new ArrayList<>();
        for (Sala sala:
             getLista()) {
            if (sala.getEstado().equals("Activa"))
                res.add(sala);
        }
        return res;
    }

    /**
     * Guarda en un XML la lista de salas.
     * @throws Exception
     */
    public void saveInXML() throws Exception {

        JAXBContext contextObj = JAXBContext.newInstance(Salas.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(this, new File("salasDB.xml"));

    }

    /**
     * Carga de un XML la lista de salas.
     * @throws JAXBException
     */
    public void loadFromXML() throws Exception {
        //TODO: Hacer el archivo en todas (ver try catch)
        try {
            File file = new File( "salasDB.xml" );
            JAXBContext jaxbContext = JAXBContext.newInstance( Salas.class );

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Salas lista = (Salas)jaxbUnmarshaller.unmarshal( file );
            setLista(lista.getLista());

            Sala.setCantidadSalas(getLista().size());

        }catch (Exception e){
            saveInXML();
        }
    }

}

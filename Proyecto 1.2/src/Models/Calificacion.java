package Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "calificacion")
public class Calificacion {
    int nota;
    String codigo;

    public Calificacion() {
    }

    public Calificacion(int nota, String codigo) {
        this.nota = nota;
        this.codigo = codigo;
    }


    @XmlElement
    public int getNota() {
        return nota;
    }

    public void setNota(int pNota) {
        nota = pNota;
    }

    @XmlElement
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String pCodigo) {
        codigo = pCodigo;
    }
}

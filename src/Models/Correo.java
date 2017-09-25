/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Clase de Correo.
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class Correo {
    private String usuarioCorreo;
    private String contrasenia;
    private String rutaArchivo;
    private String nombreArchivo;
    private String destinatarios;
    private String asunto;
    private String mensaje;
    private String organizador;

    /**
     * Constructor por defecto.
     */
    public Correo() {
    }

    /**
     * Constructor Sobrecargado
     * @param mensaje Mensaje
     * @param destino Destino
     */
    public Correo(String mensaje, String destino){
        setContrasenia("mzbbhdnxxcnjbxzp");
        setUsuarioCorreo("sistemabibliotecaitcr@gmail.com");
        setAsunto("Reserva de Sala");
        setMensaje(mensaje);
        setDestinatarios(destino.trim());
        setNombreArchivo("reserva_exitosa.jpg");
        setRutaArchivo("img_correo/reserva_exitosa.jpg");
    }


    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }


    public String getContrasenia() {
        return contrasenia;
    }


    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destino) {
        this.destinatarios = destino;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Metodo para enviar el correo.
     * @return Boolean: Se envió?
     */
    public boolean enviarCorreo(){
        try{
            Properties p= new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", this.getUsuarioCorreo());
            p.setProperty("mail.smtp.auth", "true");

            Session s=Session.getDefaultInstance(p,null);
            BodyPart texto= new MimeBodyPart();
            texto.setText(this.getMensaje());
            BodyPart adjunto= new MimeBodyPart();

            if(!this.getRutaArchivo().equals("")){
                adjunto.setDataHandler(new DataHandler(new FileDataSource(this.getRutaArchivo())));
                adjunto.setFileName(this.getNombreArchivo());
            }
            MimeMultipart m=new MimeMultipart();
            m.addBodyPart(texto);

            if(!this.getRutaArchivo().equals("")){
                m.addBodyPart(adjunto);
            }
            MimeMessage mensaje= new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(this.getUsuarioCorreo()));
            if (this.getDestinatarios().contains(","))
                mensaje.addRecipients(Message.RecipientType.CC, InternetAddress.parse(this.getDestinatarios()));
            else
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(this.getDestinatarios()));
            mensaje.setSubject(this.getAsunto());
            mensaje.setContent(m);

            Transport t=s.getTransport("smtp");
            t.connect(this.getUsuarioCorreo(),this.getContrasenia());
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;

        }catch(Exception e){
            System.out.print("ERROR"+e);
            return false;
        }

    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }
    
    
}

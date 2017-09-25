
package Utils;

import Models.Correo;

import java.util.Properties;
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

/**
 * Handler de Correos.
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class MailHandler {
    
    public boolean enviarCorreo(Correo c){
        try{
            Properties p= new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", c.getUsuarioCorreo());
            p.setProperty("mail.smtp.auth", "true");
            
            Session s=Session.getDefaultInstance(p,null);
            BodyPart texto= new MimeBodyPart();
            texto.setText(c.getMensaje());
            BodyPart adjunto= new MimeBodyPart();
            
            if(!c.getRutaArchivo().equals("")){
                adjunto.setDataHandler(new DataHandler(new FileDataSource(c.getRutaArchivo())));
                adjunto.setFileName(c.getNombreArchivo());
            }
            MimeMultipart m=new MimeMultipart();
            m.addBodyPart(texto);
            
            if(!c.getRutaArchivo().equals("")){
                m.addBodyPart(adjunto);
            }
            MimeMessage mensaje= new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(c.getUsuarioCorreo()));
            if (c.getDestinatarios().contains(","))
                mensaje.addRecipients(Message.RecipientType.CC, InternetAddress.parse(c.getDestinatarios()));
            else
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getDestinatarios()));
            mensaje.setSubject(c.getAsunto());
            mensaje.setContent(m);
            
            Transport t=s.getTransport("smtp");
            t.connect(c.getUsuarioCorreo(),c.getContrasenia());
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;
            
        }catch(Exception e){
            System.out.print("ERROR"+e);
            return false;
        }
       
    }
    
}

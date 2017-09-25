package Utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Clase para manejar SMS's
 *
 * @author Esteban Esquivel
 * @author Israel Herrera
 * @author Israel Padilla
 */
public class SMSHandler {

    public static final String ACCOUNT_SID = "ACdec3194c62b8b7019ecefaa24d40d61b";
    public static final String AUTH_TOKEN = "14c901b36cdaf85e91e24340b44bd170";

    /**
     * Metodo para enviar un codigo por SMS.
     * @param numero Numero de telefono (CR).
     * @param codigo Codigo a enviar.
     */
    public static void enviarCodigo(int numero, String codigo) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+506"+numero),
                new PhoneNumber("+17602923588"), "El codigo para calificar la sala es: "+codigo).create();
        System.out.println(message.getSid());
    }


}

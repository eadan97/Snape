package Utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class SMSHandler {

    public static final String ACCOUNT_SID = "ACdec3194c62b8b7019ecefaa24d40d61b";
    public static final String AUTH_TOKEN = "9e7ce7c3cd7f6eb9a8a540d2dc7f99d9";

    public static void ennviarCodigo(String number, String codigo) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+506"+number),
                new PhoneNumber("+7602923588"), "El codigo para calificar la sala es: "+codigo).create();
        System.out.println(message.getSid());
    }


}

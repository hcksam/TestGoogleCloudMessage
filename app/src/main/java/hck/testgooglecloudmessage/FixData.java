package hck.testgooglecloudmessage;

import java.text.SimpleDateFormat;

/**
 * Created by hck on 5/4/16.
 */
public class FixData {
    public final static String messagesFileName = "messages";

    public static final String REG_ID = "regId";
    public static final String EMAIL_ID = "eMailId";

    public final static SimpleDateFormat DefaultLongDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Php Application URL to store Reg ID created
    public static final String APP_SERVER_URL = "http://ddns.toraou.com:8888/gcm/gcm.php/?shareRegId=true";
    // Google Project Number
    public static final String GOOGLE_PROJ_ID = "541036988345";
    // Message Key
    public static final String MSG_KEY = "m";
}

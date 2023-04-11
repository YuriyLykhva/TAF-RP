package core.util;

import java.util.Base64;

public class EncoderDecoder {

    public static String getEncodedValue(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    public static String getDecodedValue(String value) {
        return new String(Base64.getDecoder().decode(value));
    }
}

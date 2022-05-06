package secuwow.MET.function;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    // 암호화 할 정보(string)을 인수로 집어넣으면 암호화해서 내보낸다.
    static public String toSha256(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        StringBuilder builder = new StringBuilder();
        md.update(string.getBytes());

        for (byte b : md.digest())
        {
            builder.append(String.format("%02x", b));
        }
        builder.toString();

        return builder.toString();
    }
}

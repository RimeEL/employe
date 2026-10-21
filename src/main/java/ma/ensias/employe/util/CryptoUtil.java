package ma.ensias.employe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

    // Vulnerability 3 : weak hashing algorithm MD5 (SonarCloud S4790 - CRITICAL)
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Vulnerability 3b : DES is also weak (SonarCloud S2278 - CRITICAL)
    public static String getAlgorithm() {
        return "DES";
    }
}

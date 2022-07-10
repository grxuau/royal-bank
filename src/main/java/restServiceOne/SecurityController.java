package restServiceOne;

import restServiceOne.RRC.UserRRC;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SecurityController {
    private static final Map<Integer, String> tokenMap = new ConcurrentHashMap<>(); //ID , token
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    private static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public static String getToken(int userID){

        if (userID == 0) {
            return "";
        }

        if (tokenMap.containsKey(userID)){
            return tokenMap.get(userID);
        }

        String token = generateNewToken();
        tokenMap.put(userID,token);
        return token;
    }
}

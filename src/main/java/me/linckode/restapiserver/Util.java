package me.linckode.restapiserver;

import java.security.SecureRandom;

public class Util {

    public static String randomString(int length){
        String charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++)
            sb.append(charSet.charAt(rnd.nextInt(charSet.length())));
        return sb.toString();

    }

}

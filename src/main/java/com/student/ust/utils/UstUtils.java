package com.student.ust.utils;

import com.student.ust.exception.InvalidEmail;
import com.student.ust.exception.InvalidPassword;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Ust utils.
 */
public class UstUtils {
    /**
     * Validate email int.
     *
     * @param email the email
     * @return the int
     */
    public static boolean validateEmail(String email){
        String regex="^([A-Za-z0-9+_.-]+)@([A-Za-z0-9]+)\\.([a-z]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            return true;
        }
        else{
            throw new InvalidEmail();
        }
    }

    /**
     * Validate password int.
     *
     * @param password the password
     * @return the int
     */
    public static boolean validatePassword(String password) {
        String regex = "^(?=(?:.*\\d){3,})(?=\\S+$)(?=.*[@#$%^&+=])(?=(?:.*[A-Za-z]){3,}).{7,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return true;
        }
        else {
            throw new InvalidPassword();
        }
    }

    /**
     * Hash password string.
     *
     * @param password the password
     * @return the string
     */
    public static String hashPassword(String password) {
        try {
            return toHexString(getSHA(password));
        }
        catch(NoSuchAlgorithmException e){
            throw new InvalidPassword();
        }
    }

    private static byte[] getSHA(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * To hex string string.
     *
     * @param hash the hash
     * @return the string
     */
    public static String toHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Sh1fT
 */

public class MD5Helper {
    /**
     * Encode la chaîne passée en paramètre avec l'algorithme MD5
     * @param key
     * @return String
     */
    public static String getEncodedPassword(String key) {
        byte[] uniqueKey = key.getBytes();
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Aïe Aïe Aïe !\n" + ex.getLocalizedMessage());
            System.exit(1);
        }
        StringBuilder hashString = new StringBuilder();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length()-1));
            } else
                hashString.append(hex.substring(hex.length()-2));
        }
        return hashString.toString();
    }

    /**
     * Test une chaîne et une valeur encodée (chaîne hexadécimale)
     * @param clearTextTestPassword
     * @param encodedActualPassword
     * @return Boolean
     */
    public static Boolean testPassword(String clearTextTestPassword,
        String encodedActualPassword) {
	return getEncodedPassword(clearTextTestPassword).equals(encodedActualPassword);
    }

    public static void main(String[] args) {
        System.out.println(getEncodedPassword("mot de passe"));
        if (testPassword("mot de passe", "729f2d8b3d3d9bc07ba349faab7fdf44"))
            System.out.println("Les passwords sont vérifiés");
    }
}
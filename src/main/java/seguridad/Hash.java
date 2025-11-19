/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;

/**
 *
 * @author fuent
 */
import java.security.MessageDigest;

public class Hash {

    // Genera hash SHA-256 de un texto
    public static String sha256(String password) {
        if (password == null) {
            password = "";
        }

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] bytes = md.digest();
            return bytesToHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar hash SHA-256", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

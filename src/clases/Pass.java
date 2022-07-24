package clases;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Pass {

    private SecretKeySpec crearClave(){
        try {
            String clave = "PooFarmacioG4";
            byte[] claveEncriptacion = clave.getBytes("UTF-8");
            
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            
            claveEncriptacion = sha.digest(claveEncriptacion);
            claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);
            
            SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");
            
            return secretKey;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Pass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String encriptar(String datos){
        try {
            SecretKeySpec secretKey = this.crearClave();
            
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            byte[] datosEncriptar = datos.getBytes("UTF-8");
            byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
            String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);
            
            return encriptado;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Pass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String desencriptar(String datosEncriptados){
        try {
            SecretKeySpec secretKey = this.crearClave();
            
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            byte[] bytesEncriptados = Base64.getDecoder().decode(datosEncriptados);
            byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
            String datos = new String(datosDesencriptados);
            
            return datos;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Pass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

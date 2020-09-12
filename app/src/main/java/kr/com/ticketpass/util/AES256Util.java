package kr.com.ticketpass.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Util {
    private String iv;
    private Key keySpec;

    public AES256Util(String key)throws UnsupportedEncodingException {
        this.iv = key.substring(0,16);

        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int length = b.length;

        if(length > keyBytes.length){
            length = keyBytes.length;
        }

        System.arraycopy(b,0,keyBytes,0,length);
        SecretKeySpec keySpec =  new SecretKeySpec(keyBytes, "AES");

        this.keySpec = keySpec;
    }

    public String aesEncode(String str) throws java.io.UnsupportedEncodingException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            IllegalBlockSizeException,
            BadPaddingException{
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));

        return new String(Base64.encodeToString(encrypted, Base64.NO_WRAP));
    }

    public String aesDecode(String str) throws java.io.UnsupportedEncodingException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            IllegalBlockSizeException,
            BadPaddingException {

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        byte[] byteStr = Base64.decode(str.getBytes(), Base64.NO_WRAP);

        return new String(c.doFinal(byteStr), "UTF-8");
    }
}

package kr.com.ticketpass.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

internal class AES256Cipher private constructor() {
    companion object {
        @Volatile
        private var INSTANCE: AES256Cipher? = null
        const val secretKey = "kik2fkas9ls1t7vrv72mb" //32bit
        var IV = "" //16bit
        val instance: AES256Cipher?
            get() {
                if (INSTANCE == null) {
                    synchronized(AES256Cipher::class.java) {
                        if (INSTANCE == null) INSTANCE =
                            AES256Cipher()
                    }
                }
                return INSTANCE
            }

        //암호화
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Throws(
            UnsupportedEncodingException::class,
            NoSuchAlgorithmException::class,
            NoSuchPaddingException::class,
            InvalidKeyException::class,
            InvalidAlgorithmParameterException::class,
            IllegalBlockSizeException::class,
            BadPaddingException::class
        )
        fun AES_Encode(str: String): String {
            val keyData = secretKey.toByteArray()
            val secureKey: SecretKey = SecretKeySpec(keyData, "AES")
            val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
            c.init(Cipher.ENCRYPT_MODE, secureKey, IvParameterSpec(IV.toByteArray()))
            val encrypted = c.doFinal(str.toByteArray(StandardCharsets.UTF_8))
            return String(Base64.getEncoder().encode(encrypted))
        }

        //복호화
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Throws(
            UnsupportedEncodingException::class,
            NoSuchAlgorithmException::class,
            NoSuchPaddingException::class,
            InvalidKeyException::class,
            InvalidAlgorithmParameterException::class,
            IllegalBlockSizeException::class,
            BadPaddingException::class
        )
        fun AES_Decode(str: String): String {
            val keyData = secretKey.toByteArray()
            val secureKey: SecretKey = SecretKeySpec(keyData, "AES")
            val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
            c.init(
                Cipher.DECRYPT_MODE,
                secureKey,
                IvParameterSpec(IV.toByteArray(StandardCharsets.UTF_8))
            )
            val byteStr = Base64.getDecoder().decode(str.toByteArray())
            return String(c.doFinal(byteStr), StandardCharsets.UTF_8)
        }
    }

    init {
        IV = secretKey.substring(0, 16)
    }
}
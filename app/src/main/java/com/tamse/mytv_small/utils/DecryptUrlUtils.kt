package vn.mytv.b2c.androidtv.common.utils

import android.content.Context
import android.text.TextUtils
import android.util.Base64
import com.tamse.mytv_small.R
import com.tamse.mytv_small.data.model.EncryptUrlModel
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object DecryptUrlUtils {
    fun decryptAES(encrypted: ByteArray, key: String, ivText: String): String {
        val ivParameterSpec = IvParameterSpec(Base64.decode(ivText, 0))
        val secretKeySpec = SecretKeySpec(key.toByteArray(), "AES")
        val cipherDecrypt = Cipher.getInstance("AES/CFB/NoPadding")
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
        val decrypted = cipherDecrypt.doFinal(encrypted)
        return String(decrypted)
    }

    fun buildUrl(ctx: Context, originalUrl: String, encryptUrlModel: EncryptUrlModel?): String {
        if (encryptUrlModel == null)
            return originalUrl
        val encrypted = Base64.decode(encryptUrlModel.c, 0)
        var rKey = ""
        var newUrl = originalUrl
        try {
            rKey = decryptAES(
                encrypted,
                encryptUrlModel.b + ctx.getString(R.string.text_private_key_decrypt_url),
                encryptUrlModel.a
            )
            if (!TextUtils.isEmpty(rKey))
                newUrl = "$originalUrl&rkey=$rKey"
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
//        Logger.d("decrypted URL: $newUrl")
        return newUrl
    }
}
package caelum.com.twittelumapp.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.util.*

object Carregador {
    fun decodifica(foto:String):Bitmap{
        val decode:ByteArray = Base64.decode(foto,Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decode,0,decode.size)
        return bitmap
    }
}
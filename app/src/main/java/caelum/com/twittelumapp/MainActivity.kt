package caelum.com.twittelumapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botaoPublicar = findViewById<Button>(R.id.publicar_tweet)
        botaoPublicar.setOnClickListener{
            val campoConteudo = findViewById<EditText>(R.id.conteudo_tweet)
            val conteudo = campoConteudo.text.toString()

            Toast.makeText(this,conteudo,Toast.LENGTH_LONG).show()
        }
    }


}
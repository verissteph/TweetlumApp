package caelum.com.twittelumapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.content.FileProvider.getUriForFile
import androidx.lifecycle.ViewModelProvider
import caelum.com.twittelumapp.bancodedados.TwittelumDatabase
import caelum.com.twittelumapp.databinding.ActivityTweetBinding
import caelum.com.twittelumapp.modelo.Tweet
import caelum.com.twittelumapp.util.decodificaParaBase64
import caelum.com.twittelumapp.vm.TweetViewModel
import caelum.com.twittelumapp.vm.ViewModelFactory
import com.google.android.material.transition.FadeProvider
import java.io.File

class TweetActivity : AppCompatActivity() {
    private var localFoto: String? = null
    private val viewModel: TweetViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(TweetViewModel::class.java)
    }
    private lateinit var binding: ActivityTweetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)
        return true
    }

//    override fun onResume() {
//        super.onResume()
//        if (localFoto != null) {
//            carregaFoto()
//        }
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.tweet_menu_cadastrar -> {
                publicaTweet()
                finish()
            }
            R.id.menu_camera -> {
                tiraFoto()
                true
            }
            android.R.id.home -> finish()
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            carregaFoto()
        }
    }

    private fun publicaTweet() {
        val tweet = criaTweet()
        viewModel.salva(tweet)
//        Toast.makeText(this, "$tweet foi salvo com sucesso :D", Toast.LENGTH_LONG).show()
        }
    fun criaTweet(): Tweet{
        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.conteudo_tweet)
        val mensagemDoTweet: String = campoDeMensagemDoTweet.text.toString()
        val foto: String? = binding.tweetFoto.tag as String?
       return Tweet(mensagemDoTweet,foto)

    }


    private fun tiraFoto() {
        val abrirCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = defineLocalDaFoto()
        abrirCamera.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)
        startActivityForResult(abrirCamera, 123)
    }

    fun defineLocalDaFoto(): Uri? {
        localFoto = "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/${
            System.currentTimeMillis()
        }.jpg"
        val arquivo = File(localFoto)
        return FileProvider.getUriForFile(this, "br.com.twittelumapp.fileprovider", arquivo)
    }

    private fun carregaFoto() {

        val bitmap = BitmapFactory.decodeFile(localFoto)
        val bm = Bitmap.createScaledBitmap(bitmap, 300, 300, true)
        binding.tweetFoto.setImageBitmap(bm)
        val fotoNaBase64 = bm.decodificaParaBase64()
        binding.tweetFoto.tag = fotoNaBase64
        binding.tweetFoto.scaleType = ImageView.ScaleType.FIT_XY
    }
}
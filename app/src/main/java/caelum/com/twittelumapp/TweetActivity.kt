package caelum.com.twittelumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import caelum.com.twittelumapp.bancodedados.TwittelumDatabase
import caelum.com.twittelumapp.modelo.Tweet
import caelum.com.twittelumapp.vm.TweetViewModel
import caelum.com.twittelumapp.vm.ViewModelFactory

class TweetActivity : AppCompatActivity() {
    private val viewModel: TweetViewModel by lazy{
        ViewModelProvider(this,ViewModelFactory).get(TweetViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
          }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tweet_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item?.itemId){
        R.id.tweet_menu_cadastrar ->{
            publicaTweet()
            finish()
        }
        R.id.menu_camera -> {
            val abrirCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(abrirCamera)
            true
        }
        android.R.id.home -> finish()
    }
        return false
    }

    private fun publicaTweet(){
        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.conteudo_tweet)
        val mensagemDoTweet: String = campoDeMensagemDoTweet.text.toString()
        val tweet = Tweet(mensagemDoTweet)
        viewModel.salva(tweet)
//        Toast.makeText(this,"$tweet foi salvot",Toast.LENGTH_LONG).show()
    }

}
package caelum.com.twittelumapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import caelum.com.twittelumapp.bancodedados.TwittelumDatabase
import caelum.com.twittelumapp.databinding.ActivityListaTweetsBinding
import caelum.com.twittelumapp.modelo.Tweet
import com.google.android.material.snackbar.Snackbar

class ListaTweetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityListaTweetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tweetDao = TwittelumDatabase.getInstance(this).tweetDao()
        val tweets: List<Tweet> = tweetDao.lista()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tweets)

        binding.listaTweet.adapter = adapter
        binding.fabNovo.setOnClickListener{
                val intencao = Intent(this, TweetActivity::class.java)
                startActivity(intencao)
        }
    }
}
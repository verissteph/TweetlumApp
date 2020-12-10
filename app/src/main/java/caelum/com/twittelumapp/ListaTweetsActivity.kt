package caelum.com.twittelumapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import caelum.com.twittelumapp.bancodedados.TwittelumDatabase
import caelum.com.twittelumapp.databinding.ActivityListaTweetsBinding
import caelum.com.twittelumapp.modelo.Tweet
import caelum.com.twittelumapp.vm.TweetViewModel
import caelum.com.twittelumapp.vm.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class ListaTweetsActivity : AppCompatActivity() {
    private val viewModel: TweetViewModel by lazy{
        ViewModelProvider(this,ViewModelFactory).get(TweetViewModel::class.java)
    }
    lateinit var  binding: ActivityListaTweetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaTweetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.lista().observe(this,observer())

        binding.fabNovo.setOnClickListener{
                val intencao = Intent(this, TweetActivity::class.java)
                startActivity(intencao)
        }
    }
    private  fun observer():Observer<List<Tweet>>{
        return Observer {
            binding.listaTweet.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,it)
        }
    }

}
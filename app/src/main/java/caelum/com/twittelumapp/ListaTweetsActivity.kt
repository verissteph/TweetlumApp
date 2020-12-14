package caelum.com.twittelumapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
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

        binding.listaTweet.setOnItemClickListener{adapter, view, position, id ->
            val builder = AlertDialog.Builder(this)
            builder.setIcon(R.drawable.ic_warning)
            builder.setTitle("Deseja deletar?")
            builder.setMessage("Tem certeza?")
            builder.setPositiveButton("Sim"){_,_ ->
                val tweet = adapter.getItemAtPosition(position) as Tweet
                viewModel.deleta(tweet)
            }
            builder.setNegativeButton("Não"){_,_ ->}
            builder.setNeutralButton("Não sei"){_,_ ->}
            builder.show()
        }

       viewModel.lista().observe(this,observer())

        binding.fabNovo.setOnClickListener{
                val intencao = Intent(this, TweetActivity::class.java)
                startActivity(intencao)
        }
    }
    private  fun observer():Observer<List<Tweet>>{
        return Observer {
            tweets ->
            tweets?.let{
                binding.listaTweet.adapter = TweetAdapter(tweets)
            }
        }
    }

}
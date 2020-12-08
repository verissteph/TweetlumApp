package caelum.com.twittelumapp

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import caelum.com.twittelumapp.databinding.ActivityListaTweetsBinding

class ListaTweetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityListaTweetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tweets = listOf<String>(
            "tweet 1",
            "tweet 2",
            "tweet 3",
            "tweet 4",
            "tweet 5",
            "tweet 6",
            "tweet 7"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tweets)

binding.listaTweet.adapter = adapter
    }
}
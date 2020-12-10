package caelum.com.twittelumapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import caelum.com.twittelumapp.application.TwittelumAplication
import caelum.com.twittelumapp.bancodedados.TwittelumDatabase
import caelum.com.twittelumapp.bancodedados.repository.TweetRepository

object ViewModelFactory: ViewModelProvider.Factory {
    private val database = TwittelumDatabase.getInstance(TwittelumAplication.getInstance())
    private val tweetRepository = TweetRepository(database.tweetDao())
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = TweetViewModel(tweetRepository) as T
}
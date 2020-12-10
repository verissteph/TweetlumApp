package caelum.com.twittelumapp.vm

import androidx.lifecycle.ViewModel
import caelum.com.twittelumapp.bancodedados.repository.TweetRepository
import caelum.com.twittelumapp.modelo.Tweet

class TweetViewModel(private val repository: TweetRepository): ViewModel() {
    fun lista() = repository.lista()
    fun salva(tweet: Tweet) = repository.salva(tweet)
}
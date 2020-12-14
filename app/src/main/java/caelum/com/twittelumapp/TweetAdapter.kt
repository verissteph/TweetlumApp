package caelum.com.twittelumapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import caelum.com.twittelumapp.databinding.ItemTweetBinding
import caelum.com.twittelumapp.extensions.Carregador
import caelum.com.twittelumapp.modelo.Tweet

class TweetAdapter(private val tweets: List<Tweet>): BaseAdapter() {
    override fun getCount(): Int {
        return tweets.size
    }

    override fun getItem(position: Int): Any {
        return tweets[position]
    }

    override fun getItemId(position: Int): Long {
        return  tweets[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tweet = tweets[position]
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ItemTweetBinding.inflate(inflater, parent, false)
        binding.itemTweetTexto.text = tweet.mensagem
        tweet.foto?.let{
            binding.itemTweetFoto.visibility = View.VISIBLE
            binding.itemTweetFoto.setImageBitmap(Carregador.decodifica(it))
             }
        return  binding.root
    }
}
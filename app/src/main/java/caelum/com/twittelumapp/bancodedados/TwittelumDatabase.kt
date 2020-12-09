package caelum.com.twittelumapp.bancodedados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import caelum.com.twittelumapp.modelo.Tweet

@Database(entities = [Tweet::class],version = 1)
abstract  class TwittelumDatabase: RoomDatabase() {
    abstract  fun tweetDao(): TweetDao
    companion object{
        private  var database: TwittelumDatabase? = null
        private val DATABASE = "TwittelumDB"
        fun getInstance(context: Context) : TwittelumDatabase{
            return database ?: criaBanco(context).also { database = it}
        }
        private fun criaBanco(context:Context): TwittelumDatabase{
            return Room.databaseBuilder(context,TwittelumDatabase::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()
        }
    }
}
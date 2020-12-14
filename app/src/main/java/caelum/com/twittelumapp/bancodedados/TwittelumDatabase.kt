package caelum.com.twittelumapp.bancodedados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import caelum.com.twittelumapp.modelo.Tweet

@Database(entities = [Tweet::class],version = 2)
abstract  class TwittelumDatabase: RoomDatabase() {
    abstract  fun tweetDao(): TweetDao
    companion object{
        private  var database: TwittelumDatabase? = null
        fun getInstance(context: Context) : TwittelumDatabase{
            return database ?: criaBanco(context).also { database = it}
        }
        private fun criaBanco(context:Context): TwittelumDatabase{
            return Room.databaseBuilder(context,TwittelumDatabase::class.java, "TwittelumDB")
                .allowMainThreadQueries()
                .addMigrations(Migration1Para2)
                .build()
        }
        object Migration1Para2 : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                val sql = "alter table Tweet add column foto text"
                database.execSQL(sql)
            }
        }
    }
}
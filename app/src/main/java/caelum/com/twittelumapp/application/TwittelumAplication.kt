package caelum.com.twittelumapp.application

import android.app.Application

class TwittelumAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object{
        private  lateinit var instance: TwittelumAplication
        fun getInstance() = instance
    }
}
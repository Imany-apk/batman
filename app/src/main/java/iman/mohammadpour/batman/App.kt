package iman.mohammadpour.batman

import android.app.Application
import iman.mohammadpour.batman.di.dataModule
import iman.mohammadpour.batman.di.networkModule
import iman.mohammadpour.batman.di.repositoryModule
import org.koin.android.ext.android.startKoin

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            this, listOf(
                repositoryModule, networkModule, dataModule
            )
        )
    }
}
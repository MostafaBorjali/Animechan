package ir.borjali.animechan.core

import android.app.Application
import ir.borjali.animechan.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


open class AnimeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AnimeApp)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    networkModule,
                    databaseModule
                )
            )

        }

    }
}
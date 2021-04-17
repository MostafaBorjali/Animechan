package ir.borjali.animechan.di

import android.app.Application
import androidx.room.Room
import ir.borjali.animechan.data.db.QuoteDao
import ir.borjali.animechan.data.db.QuotesDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): QuotesDataBase {
        return Room.databaseBuilder(application, QuotesDataBase::class.java, "quote")
            .build()
    }

    fun provideQuoteDao(database: QuotesDataBase): QuoteDao {
        return database.quoteDao()
    }



    single { provideDatabase(androidApplication()) }
    single { provideQuoteDao(get()) }


}
package ir.borjali.animechan.di


import android.content.Context
import ir.borjali.animechan.data.remote.AnimeApi
import ir.borjali.animechan.data.db.QuoteDao
import ir.borjali.animechan.data.repository.QuoteRepositoryImpl
import ir.borjali.animechan.domain.repository.QuoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideQuoteRepository(api: AnimeApi, context: Context, dao: QuoteDao): QuoteRepository {
        return QuoteRepositoryImpl(api, context, dao)
    }
    single { provideQuoteRepository(get(), androidContext(), get()) }

}
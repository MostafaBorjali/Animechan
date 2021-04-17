package ir.borjali.animechan.data.repository

import android.content.Context
import android.util.Log
import ir.borjali.animechan.data.remote.AnimeApi
import ir.borjali.animechan.data.db.QuoteDao
import ir.borjali.animechan.domain.model.Quote
import ir.borjali.animechan.domain.repository.QuoteRepository
import ir.borjali.animechan.domain.model.AppResult
import ir.borjali.animechan.util.NetworkManager.isOnline
import ir.borjali.animechan.util.TAG
import ir.borjali.animechan.util.Utils.handleApiError
import ir.borjali.animechan.util.Utils.handleSuccess
import ir.borjali.animechan.util.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepositoryImpl(
    private val api: AnimeApi,
    private val context: Context,
    private val dao: QuoteDao
) :
    QuoteRepository {

    override suspend fun getQuotes(): AppResult<List<Quote>> {
        if (isOnline(context)) {
            return try {
                val response = api.getRandomQuote()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.add(it) }
                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
            //check in db if the data exists
            val data = getQuoteFromCache()
            return if (data.isNotEmpty()) {
                Log.d(TAG, "from db")
                AppResult.Success(data)
            } else
            //no network
                context.noNetworkConnectivityError()
        }
    }

    private suspend fun getQuoteFromCache(): List<Quote> {
        return withContext(Dispatchers.IO) {
            dao.getListOfQuotes()
        }
    }

}
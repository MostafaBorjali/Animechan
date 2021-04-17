package ir.borjali.animechan.domain.repository

import ir.borjali.animechan.domain.model.AppResult
import ir.borjali.animechan.domain.model.Quote

interface QuoteRepository {
    suspend fun getQuotes() : AppResult<List<Quote>>
}

package ir.borjali.animechan.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.borjali.animechan.domain.model.Quote

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote ")
    fun getListOfQuotes(): List<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(quotes: List<Quote>)
}
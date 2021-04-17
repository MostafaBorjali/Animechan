package ir.borjali.animechan.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.borjali.animechan.domain.model.Quote

@Database(entities = [Quote::class], version = 1,exportSchema = false)
abstract class QuotesDataBase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
}

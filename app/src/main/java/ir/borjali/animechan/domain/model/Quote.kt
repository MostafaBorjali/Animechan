package ir.borjali.animechan.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "quote")
@Parcelize
data class Quote(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val anime: String,
    val character: String,
    val quote: String
) : Parcelable
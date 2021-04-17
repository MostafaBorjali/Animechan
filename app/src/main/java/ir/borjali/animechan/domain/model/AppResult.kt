package ir.borjali.animechan.domain.model

import java.lang.Exception

/**
 * AppResult class is a wrapper class that helps to handle success and failure scenarios with co routines
 */
sealed class AppResult<out T> {

    data class Success<out T>(val successData : T) : AppResult<T>()
    class Error(val exception: Exception)
        : AppResult<Nothing>()

}
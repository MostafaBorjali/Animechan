package ir.borjali.animechan.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.borjali.animechan.domain.model.AppResult
import ir.borjali.animechan.domain.model.Quote
import ir.borjali.animechan.domain.repository.QuoteRepository
import ir.borjali.animechan.util.SingleLiveEvent
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    val showLoading = MutableLiveData<Boolean>()
    val quotesList = MutableLiveData<List<Quote>>()
    val showError = SingleLiveEvent<String>()

    init {

        getRandomQuotes()
    }

    fun getRandomQuotes() {
        showLoading.value = true
        viewModelScope.launch {
            val result = repository.getQuotes()
            showLoading.value = false
            when (result) {
                is AppResult.Success -> {
                    quotesList.value = result.successData!!
                    showError.value = ""
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}
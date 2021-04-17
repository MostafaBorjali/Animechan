package ir.borjali.animechan.di

import ir.borjali.animechan.presentation.QuoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build QuoteViewModel
    viewModel { QuoteViewModel(repository = get()) }

}
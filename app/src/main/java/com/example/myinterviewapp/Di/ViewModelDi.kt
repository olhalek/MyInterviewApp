package com.example.myinterviewapp.Di

import com.example.myinterviewapp.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (MainViewModel(get())) }
}
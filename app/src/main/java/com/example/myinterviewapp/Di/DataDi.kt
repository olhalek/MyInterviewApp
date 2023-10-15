package com.example.myinterviewapp

import com.example.myinterviewapp.repository.GifRepository
import com.example.myinterviewapp.repository.GifRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<GifRepository> { GifRepositoryImpl() }
}
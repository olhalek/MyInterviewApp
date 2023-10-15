package com.example.myinterviewapp.repository

import com.example.myinterviewapp.model.DataObject
import java.util.*

interface GifRepository {

    suspend fun getGifs(): Optional<List<DataObject>>
}
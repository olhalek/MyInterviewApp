package com.example.myinterviewapp.api


import com.example.myinterviewapp.model.GetImageResult
import retrofit2.Call
import retrofit2.http.GET

interface GifApi {

    @GET("gifs/trending?api_key=O8KXiYmpAZw71pE2lvGODyrt1fVni9XK")
    fun get(): Call<GetImageResult>
}
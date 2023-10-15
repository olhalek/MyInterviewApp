package com.example.myinterviewapp.repository

import android.util.Log
import com.example.myinterviewapp.api.GifApi
import com.example.myinterviewapp.model.DataObject
import com.example.myinterviewapp.model.GetImageResult
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class GifRepositoryImpl : GifRepository {

    override suspend fun getGifs(): Optional<List<DataObject>> {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return suspendCancellableCoroutine { continuation ->
            val service = retrofit.create(GifApi::class.java)

            service.get().enqueue(object : Callback<GetImageResult> {
                override fun onResponse(
                    call: Call<GetImageResult>,
                    response: Response<GetImageResult>
                ) {
                    val body = response.body()

                    if (body == null) {
                        Log.e(TAG, "Can't find gifs because request response body is null")

                         continuation.resume(Optional.empty()) {
                             handleApiError()
                         }
                    }

                    continuation.resume(Optional.of(body!!.result)){
                        handleApiError()
                    }
                }

                override fun onFailure(call: Call<GetImageResult>, t: Throwable) {
                    Log.e(TAG, "Request response if failure, the problem is: ${t.message}")

                    continuation.resume(Optional.empty()){
                        handleApiError()
                    }
                }
            })
        }
    }

    fun handleApiError() {
        Log.e(TAG, "The request was ended with failure")
    }

    companion object {

        private const val TAG = "GifRepositoryImpl"

        private const val BASE_URL = "https://api.giphy.com/v1/"

    }
}
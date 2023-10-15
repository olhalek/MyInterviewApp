package com.example.myinterviewapp.model

import com.google.gson.annotations.SerializedName

data class DataImage(
    @SerializedName("original")
    val originalImage: OriginalImage
)
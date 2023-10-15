package com.example.myinterviewapp.model

import com.google.gson.annotations.SerializedName

data class GetImageResult(
    @SerializedName("data")
    val result:List<DataObject>
)


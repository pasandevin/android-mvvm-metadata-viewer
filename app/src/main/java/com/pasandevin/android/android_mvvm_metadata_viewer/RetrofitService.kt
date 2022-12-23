package com.pasandevin.android.android_mvvm_metadata_viewer

import com.pasandevin.android.android_mvvm_metadata_viewer.Models.NetworkVideoContainer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface DevByteService {
    @GET("devbytes")
    suspend fun getPlaylist(): NetworkVideoContainer
}
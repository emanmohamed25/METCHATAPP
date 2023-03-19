package com.example.chatapp.doctor.newchat.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientAdmin {

    private val BASE_URL = "https://orcafood.test/api/v1/"
    private var retrofitClient: RetrofitClientAdmin? = null
    private var retrofitInstance: Retrofit? = null

    private fun RetrofitClient() {}

  public  fun getRetrofitClientInstance(): RetrofitClientAdmin? {
        if (retrofitClient == null)
            retrofitClient = RetrofitClientAdmin()
        return retrofitClient
    }

   public fun getRetrofit(): Retrofit? {
        if (retrofitInstance == null) {
            retrofitInstance =
                Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofitInstance
    }

}
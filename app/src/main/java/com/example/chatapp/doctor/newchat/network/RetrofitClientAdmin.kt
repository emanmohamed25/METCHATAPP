package com.example.chatapp.doctor.newchat.network

import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientAdmin {

    companion object {
        private val retrofit by lazy {
            val okhttp = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            okhttp.addInterceptor(logging)


            val client = OkHttpClient.Builder()
                .addInterceptor(logging).build()
            val gson =GsonBuilder().setLenient().create()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build()
        }
        val api by lazy {
            retrofit.create(ApiService::class.java)
        }
    }


//    private val BASE_URL = "https://orcafood.test/api/v1/"
//    private var retrofitClient: RetrofitClientAdmin? = null
//    private var retrofitInstance: Retrofit? = null
//
//    private fun RetrofitClient() {}
//
//  public  fun getRetrofitClientInstance(): RetrofitClientAdmin? {
//        if (retrofitClient == null)
//            retrofitClient = RetrofitClientAdmin()
//        return retrofitClient
//    }
//
//   public fun getRetrofit(): Retrofit? {
//        if (retrofitInstance == null) {
//            retrofitInstance =
//                Retrofit.Builder().baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//        }
//        return retrofitInstance
//    }

}
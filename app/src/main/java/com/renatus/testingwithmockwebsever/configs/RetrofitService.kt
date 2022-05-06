package com.renatus.testingwithmockwebsever.configs

import retrofit2.Retrofit
import com.renatus.testingwithmockwebsever.models.services.contract.ApiUrls
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    @JvmStatic
    fun <S> createService(serviceClass: Class<S>?): S {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiUrls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(serviceClass)
    }
}
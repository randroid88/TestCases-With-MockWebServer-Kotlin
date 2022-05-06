package com.renatus.testingwithmockwebsever.models.repositories

import com.renatus.testingwithmockwebsever.configs.RetrofitService.createService
import com.renatus.testingwithmockwebsever.models.services.contract.APIService
import androidx.lifecycle.MutableLiveData
import com.renatus.testingwithmockwebsever.models.responses.MovieCharacterResponse
import com.renatus.testingwithmockwebsever.models.repositories.MovieCharacterRepository
import com.renatus.testingwithmockwebsever.configs.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieCharacterRepository private constructor() {
    private val apiService: APIService
    val movieCharacterDetails: MutableLiveData<MovieCharacterResponse?>
        get() {
            val movieCharacterResponseMutableLiveData = MutableLiveData<MovieCharacterResponse?>()
            apiService.getMovieCharacterData(1)?.enqueue(object : Callback<MovieCharacterResponse?> {
                override fun onResponse(
                    call: Call<MovieCharacterResponse?>,
                    response: Response<MovieCharacterResponse?>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }
                    val movieCharacterResponses = response.body()
                    movieCharacterResponseMutableLiveData.value = movieCharacterResponses
                }

                override fun onFailure(call: Call<MovieCharacterResponse?>, throwable: Throwable) {
                    val movieCharacterResponse = MovieCharacterResponse()
                    movieCharacterResponse.errorCode = 404
                    movieCharacterResponseMutableLiveData.value = movieCharacterResponse
                }
            })
            return movieCharacterResponseMutableLiveData
        }

    companion object {
        private var newsRepository: MovieCharacterRepository? = null
        val instance: MovieCharacterRepository?
            get() {
                if (newsRepository == null) {
                    newsRepository = MovieCharacterRepository()
                }
                return newsRepository
            }
    }

    init {
        apiService = createService(APIService::class.java)
    }
}
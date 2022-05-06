package com.renatus.testingwithmockwebsever.models.repositories

import com.renatus.testingwithmockwebsever.configs.RetrofitService.createService
import com.renatus.testingwithmockwebsever.models.services.contract.APIService
import androidx.lifecycle.MutableLiveData
import com.renatus.testingwithmockwebsever.models.responses.MovieDetailResponse
import com.renatus.testingwithmockwebsever.models.repositories.MovieDetailRepository
import com.renatus.testingwithmockwebsever.configs.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailRepository private constructor() {
    private val apiService: APIService
    val movieCharacterDetails: MutableLiveData<MovieDetailResponse?>
        get() {
            val movieDetailResponseMutableLiveData = MutableLiveData<MovieDetailResponse?>()
            apiService.getMovieDetails(1)?.enqueue(object : Callback<MovieDetailResponse?> {
                override fun onResponse(
                    call: Call<MovieDetailResponse?>,
                    response: Response<MovieDetailResponse?>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }
                    val movieDetailResponse = response.body()
                    movieDetailResponseMutableLiveData.value = movieDetailResponse
                }

                override fun onFailure(call: Call<MovieDetailResponse?>, throwable: Throwable) {
                    val MovieDetailResponse = MovieDetailResponse()
                    MovieDetailResponse.errorCode = 404
                    movieDetailResponseMutableLiveData.value = MovieDetailResponse
                }
            })
            return movieDetailResponseMutableLiveData
        }

    companion object {
        private var newsRepository: MovieDetailRepository? = null
        val instance: MovieDetailRepository?
            get() {
                if (newsRepository == null) {
                    newsRepository = MovieDetailRepository()
                }
                return newsRepository
            }
    }

    init {
        apiService = createService(APIService::class.java)
    }
}
package com.renatus.testingwithmockwebsever.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.renatus.testingwithmockwebsever.models.responses.MovieDetailResponse
import com.renatus.testingwithmockwebsever.models.repositories.MovieDetailRepository

class MovieDetailsViewModel(application: Application) : AndroidViewModel(
    application
) {
    var movieDetails: MutableLiveData<MovieDetailResponse?>? = null
        private set

    fun initMovieDetailsAPI() {
        if (movieDetails != null) {
            return
        }
        val movieDetailRepository = MovieDetailRepository.instance
        movieDetails = movieDetailRepository?.movieCharacterDetails
    }
}
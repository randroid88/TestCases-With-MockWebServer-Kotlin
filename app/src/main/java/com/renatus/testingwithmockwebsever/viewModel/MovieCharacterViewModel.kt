package com.renatus.testingwithmockwebsever.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.renatus.testingwithmockwebsever.models.responses.MovieCharacterResponse
import com.renatus.testingwithmockwebsever.models.repositories.MovieCharacterRepository

class MovieCharacterViewModel(application: Application) : AndroidViewModel(
    application
) {
    var movieCharacterDetails: MutableLiveData<MovieCharacterResponse?>? = null
        private set

    fun initMovieCharacterDetailsAPI() {
        if (movieCharacterDetails != null) {
            return
        }
        val commentsRepository = MovieCharacterRepository.instance
        movieCharacterDetails = commentsRepository?.movieCharacterDetails
    }
}
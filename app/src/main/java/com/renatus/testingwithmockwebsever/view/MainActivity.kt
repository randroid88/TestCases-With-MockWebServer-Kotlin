package com.renatus.testingwithmockwebsever.view

import androidx.appcompat.app.AppCompatActivity
import com.renatus.testingwithmockwebsever.viewModel.MovieCharacterViewModel
import com.renatus.testingwithmockwebsever.viewModel.MovieDetailsViewModel
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.renatus.testingwithmockwebsever.R
import androidx.lifecycle.ViewModelProviders
import com.renatus.testingwithmockwebsever.databinding.ActivityMainBinding
import com.renatus.testingwithmockwebsever.models.responses.MovieDetailResponse
import com.renatus.testingwithmockwebsever.models.responses.MovieCharacterResponse

class MainActivity : AppCompatActivity() {
    var movieCharacterViewModel: MovieCharacterViewModel? = null
    var movieDetailsViewModel: MovieDetailsViewModel? = null
    var activityMainBinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        movieCharacterViewModel = ViewModelProviders.of(this).get(
            MovieCharacterViewModel::class.java
        )
        movieDetailsViewModel = ViewModelProviders.of(this).get(
            MovieDetailsViewModel::class.java
        )
        movieDetailsViewModel!!.initMovieDetailsAPI()
        movieDetailsViewModel!!.movieDetails!!.observe(
            this,
            Observer { movieDetailResponse: MovieDetailResponse? ->
                if (movieDetailResponse != null) {
                    activityMainBinding?.tvMovieDetails?.text = movieDetailResponse.title
                }
            })
        movieCharacterViewModel!!.initMovieCharacterDetailsAPI()
        movieCharacterViewModel!!.movieCharacterDetails!!.observe(
            this,
            Observer { movieCharacterResponses: MovieCharacterResponse? ->
                if (movieCharacterResponses != null) {
                    activityMainBinding?.tvCharacterDetails?.text = movieCharacterResponses.name
                }
            })
    }
}
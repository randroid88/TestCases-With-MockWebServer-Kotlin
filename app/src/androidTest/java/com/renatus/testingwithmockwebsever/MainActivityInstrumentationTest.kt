package com.renatus.testingwithmockwebsever

import androidx.test.core.app.ActivityScenario
import com.renatus.testingwithmockwebsever.view.MainActivity
import okhttp3.mockwebserver.MockWebServer
import kotlin.Throws
import okhttp3.mockwebserver.RecordedRequest
import okhttp3.mockwebserver.MockResponse
import com.renatus.testingwithmockwebsever.RestServiceTestHelper
import org.junit.Before
import com.renatus.testingwithmockwebsever.models.services.contract.ApiUrls
import androidx.test.core.app.ActivityScenario.ActivityAction
import com.renatus.testingwithmockwebsever.viewModel.MovieDetailsViewModel
import androidx.lifecycle.ViewModelProviders
import com.renatus.testingwithmockwebsever.viewModel.MovieCharacterViewModel
import org.awaitility.Awaitility
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import com.renatus.testingwithmockwebsever.R
import okhttp3.mockwebserver.Dispatcher
import org.junit.After
import org.junit.Assert
import org.junit.Test
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MainActivityInstrumentationTest {
    private val context =
        androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().context

    private var activityScenario: ActivityScenario<MainActivity>? = null
    private var mockWebServer: MockWebServer? = null
    private val dispatcher: Dispatcher = object : Dispatcher() {
        @Throws(InterruptedException::class)
        override fun dispatch(request: RecordedRequest): MockResponse {
            if (request.path == "/films/1/") {
                val fileName = "movie_details_response.json"
                try {
                    return MockResponse().setResponseCode(200).setBody(
                        RestServiceTestHelper.getStringFromFile(
                            context, fileName
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (request.path == "/people/1/") {
                val fileName = "movie_character_response.json"
                try {
                    return MockResponse().setResponseCode(200).setBody(
                        RestServiceTestHelper.getStringFromFile(
                            context, fileName
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return MockResponse().setResponseCode(404)
        }
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer!!.dispatcher = dispatcher
        mockWebServer!!.start()
        ApiUrls.BASE_URL = mockWebServer!!.url("/").toString()
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun checkTextFieldsWithAPIResponse() {
        activityScenario!!.onActivity { activity: MainActivity ->
            val movieDetailsViewModel = ViewModelProvider(activity)[MovieDetailsViewModel::class.java]
            val movieCharacterViewModel = ViewModelProvider(activity)[MovieCharacterViewModel::class.java]
            Awaitility.await().atMost(5, TimeUnit.SECONDS).until {
                (movieDetailsViewModel.movieDetails?.value != null
                        && movieCharacterViewModel.movieCharacterDetails?.value != null)
            }
            val tvMovieDetails = activity.findViewById<AppCompatTextView>(R.id.tv_movie_details)
            val tvCharacterDetails =
                activity.findViewById<AppCompatTextView>(R.id.tv_character_details)
            val movieDetailsText = tvMovieDetails.text as String
            val characterDetailsText = tvCharacterDetails.text as String
            Assert.assertEquals("StarWar - A New Hope", movieDetailsText)
            Assert.assertEquals("Luke Skywalker.", characterDetailsText)
            Assert.assertEquals(2, mockWebServer!!.requestCount.toLong())
        }
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mockWebServer!!.shutdown()
    }
}
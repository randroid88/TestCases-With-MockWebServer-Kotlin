package com.renatus.testingwithmockwebsever.models.responses

import com.google.gson.annotations.SerializedName

class MovieDetailResponse : ErrorCode() {
    @SerializedName("title")
    val title: String? = null

    @SerializedName("episode_id")
    val episodeId: String? = null

    @SerializedName("opening_crawl")
    val openingCrawl: String? = null

    @SerializedName("director")
    val director: String? = null

    @SerializedName("producer")
    val producer: String? = null

    @SerializedName("release_date")
    val releaseDate: String? = null
    override fun toString(): String {
        return "MovieDetailResponse{" +
                "title='" + title + '\'' +
                ", episodeId='" + episodeId + '\'' +
                ", openingCrawl='" + openingCrawl + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}'
    }
}
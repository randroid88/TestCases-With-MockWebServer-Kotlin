package com.renatus.testingwithmockwebsever.models.responses

import com.google.gson.annotations.SerializedName

class MovieCharacterResponse : ErrorCode() {
    @SerializedName("name")
    val name: String? = null

    @SerializedName("hair_color")
    val hairColor: String? = null

    @SerializedName("gender")
    val gender: String? = null

    @SerializedName("height")
    val height: String? = null

    @SerializedName("skin_color")
    val skinColor: String? = null

    @SerializedName("eye_color")
    val eyeColor: String? = null
    override fun toString(): String {
        return "MovieCharacterResponse{" +
                "name='" + name + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", gender='" + gender + '\'' +
                ", height='" + height + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                '}'
    }
}
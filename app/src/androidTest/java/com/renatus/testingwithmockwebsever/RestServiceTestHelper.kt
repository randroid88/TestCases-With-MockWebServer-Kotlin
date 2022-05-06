package com.renatus.testingwithmockwebsever

import android.content.Context
import kotlin.Throws
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

internal object RestServiceTestHelper {
    @Throws(Exception::class)
    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line).append("\n")
        }
        reader.close()
        return stringBuilder.toString()
    }

    @Throws(Exception::class)
    fun getStringFromFile(context: Context, filePath: String?): String {
        val stream = context.resources.assets.open(filePath!!)
        val text = convertStreamToString(stream)
        stream.close()
        return text
    }
}
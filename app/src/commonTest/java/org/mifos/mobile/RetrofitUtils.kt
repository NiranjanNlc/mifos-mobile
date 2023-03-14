package org.mifos.mobile

import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by dilpreet on 29/7/17.
 */
object RetrofitUtils {
    fun get404Exception(): Exception {
        return HttpException(getResponseForError(404, "Not Found"))
    }

    fun get401Exception(): Exception {
        return HttpException(getResponseForError(401, "Unauthorized"))
    }

    //generate response for error based on code and messaghe
    private fun getResponseForError(errorCode: Int, message : String ): Response<Any> {
        val responseBody = ResponseBody.create(MediaType.parse("application/json"), "{\"message\":\"$message\"}")
        val response = okhttp3.Response.Builder().code(errorCode)
            .message(message)
            .protocol(Protocol.HTTP_1_1)
            .request(okhttp3.Request.Builder().url("http://localhost/").build())
            .build()
        return Response.error(responseBody,response)
    }
}
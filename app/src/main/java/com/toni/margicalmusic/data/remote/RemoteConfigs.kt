package com.toni.margicalmusic.data.remote


sealed class ApiResponse<T> {
    data class Error<T>(val message: String) : ApiResponse<T>()
    data class Data<T>(val t: T) : ApiResponse<T>()
}


suspend fun <T> fetchData( block: suspend () -> ApiResponse<T> ): ApiResponse<T> {
    return try{
        block.invoke()
    }catch (e: Exception){
        ApiResponse.Error(e.message ?: "Something went wrong")
    }
}
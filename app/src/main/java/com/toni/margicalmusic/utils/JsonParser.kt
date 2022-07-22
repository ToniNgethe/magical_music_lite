package com.toni.margicalmusic.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import timber.log.Timber
import java.lang.reflect.Type

interface JsonParser {
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj: T, type: Type): String?
}

object MoshiParser : JsonParser {
    private val moshi = Moshi.Builder().build()

    override fun <T> fromJson(json: String, type: Type): T? {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(type)
        return jsonAdapter.fromJson(json)
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(type)
        return jsonAdapter.toJson(obj)
    }

}
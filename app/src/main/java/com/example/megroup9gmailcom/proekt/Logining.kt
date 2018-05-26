package com.example.megroup9gmailcom.proekt

import com.google.gson.Gson
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.experimental.CoroutineContext

/*
fun loadingPhotosFromCloud(
    coroutineContext: CoroutineContext = CommonPool
): Deferred<User> = async(coroutineContext) {
    // Создать клиент для HTTP запросов
    val httpClient = OkHttpClient()

    // Создать запрос
    //From the emulator, 127.0.0.1 refers to the emulator itself - not your local machine. You need to use ip 10.0.2.2, which is bridged to your local machine.
    val request = Request.Builder()
            //http://jsonplaceholder.typicode.com/photos
        .url("http://10.0.2.2:8080/torg/getUser?login=user&pass=pass")
        .build()

    httpClient.newCall(request).execute().use {
        Gson().fromJson(it.body()!!.string(), User::class.java)
    }
}
*/

fun loadingPhotosFromCloud(
        coroutineContext: CoroutineContext = CommonPool
): Deferred<List<Photo>> = async(coroutineContext) {
    // Создать клиент для HTTP запросов
    val httpClient = OkHttpClient()

    // Создать запрос
    //From the emulator, 127.0.0.1 refers to the emulator itself - not your local machine. You need to use ip 10.0.2.2, which is bridged to your local machine.
    val request = Request.Builder()
            //http://jsonplaceholder.typicode.com/photos
            .url("http://10.0.2.2:8080/torg/ping")
            .build()

    httpClient.newCall(request).execute().use {
        Gson().fromJson(it.body()!!.string(), Photo.List::class.java)
    }
}
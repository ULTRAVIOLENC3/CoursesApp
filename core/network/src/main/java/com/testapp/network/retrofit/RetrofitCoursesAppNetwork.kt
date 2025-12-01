package com.testapp.network.retrofit

/*
import com.testapp.network.NetworkDataSource
import com.testapp.network.model.NetworkCourse
import dagger.Lazy
import javax.inject.Inject
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

private interface RetrofitUserApi {
    @GET(value = "courses")
    suspend fun getUsers(): List<NetworkCourse>
}

class RetrofitCoursesAppNetwork @Inject constructor(
    private val networkJson: Json,
    okhttpCallFactory: Lazy<Call.Factory>
) : NetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("") // Сюда воткнуть Url API
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(RetrofitUserApi::class.java)

    override suspend fun getCourses(): List<NetworkCourse> = networkApi.getUsers()
}*/

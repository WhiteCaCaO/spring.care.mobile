package com.example.elderlycare.mypage.service

import com.example.elderlycare.mypage.vo.SeniorDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeniorPageService {

    @GET("seniorInfo/{userId}")
    fun SeniorInfo(@Path("userId") userId: Long): Call<SeniorDTO>

    @GET("userId")
    suspend fun getUserId(@Query("email") email: String): Call<Long>
}
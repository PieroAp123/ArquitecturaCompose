package com.everis.data.network

import com.everis.data.network.entities.LoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by wildercs on 2019-10-22.
 * Copyright (c) 2019 WM Technology. All rights reserved.
 **/

interface ApiConfig {

    @Headers("Content-Type: application/json")
    @POST("api.php")
    fun doLogin(@Body params: RequestBody): Call<LoginResponse>
}
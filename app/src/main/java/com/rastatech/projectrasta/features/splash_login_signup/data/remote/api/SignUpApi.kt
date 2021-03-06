package com.rastatech.projectrasta.features.splash_login_signup.data.remote.api

import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.ErrorDTO
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    //Api Call Sample
    //https://shielded-retreat-23705.herokuapp.com/api/auth/signup


    // This needs to read different Body types for ErrorDTO, For Now Unit muna //Update Pwede ko gamitin Map as return
    @POST("api/auth/signup") // API Call for Adding User to Remote
    suspend fun createUser(@Body user: UserRequestDTO
    ): Response<Map<String,String>>

}
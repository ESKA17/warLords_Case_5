package kz.jusan.tinderformentors.data.remote

import kz.jusan.tinderformentors.domain.entitites.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TinderForMentorsApi {

    @POST("auth")
    suspend fun login(
        @Body login: Login
    ): Response<String>

    @POST("register")
    suspend fun register(
        @Body register: Register
    ): Response<String>

    @POST("process_register")
    suspend fun confirmEmail(
        @Body email: ConfirmationEmail
    ): Response<String>

    @POST("user_info")
    suspend fun submitRegistrationDetails(
        @Header("token") token: String,
        @Body registrationDetails: RegistrationDetails
    ): Response<String>

    @POST("user_info/majors")
    suspend fun submitMajorsList(
        @Header("token") token: String,
        @Body majors: Majors
    ): Response<String>

}
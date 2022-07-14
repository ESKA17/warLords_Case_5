package kz.jusan.tinderformentors.domain.repository

import kz.jusan.tinderformentors.domain.entitites.*
import retrofit2.Response

interface TinderForMentorsRepository {

    suspend fun login(login: Login): Response<String>

    suspend fun register(register: Register): Response<String>

    suspend fun confirmEmail(confirmationEmail: ConfirmationEmail): Response<String>

    suspend fun submitRegistrationDetails(token: String, registrationDetails: RegistrationDetails): Response<String>

    suspend fun submitMajorsList(token: String, majors: Majors): Response<String>
}
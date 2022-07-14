package kz.jusan.tinderformentors.data.repository

import kz.jusan.tinderformentors.data.remote.TinderForMentorsApi
import kz.jusan.tinderformentors.domain.entitites.*
import kz.jusan.tinderformentors.domain.repository.TinderForMentorsRepository
import retrofit2.Response
import javax.inject.Inject

class TinderForMentorsRepositoryImpl @Inject constructor(
    private val api: TinderForMentorsApi
) : TinderForMentorsRepository {

    override suspend fun login(login: Login): Response<String> {
        return api.login(login)
    }

    override suspend fun register(register: Register): Response<String> {
        return api.register(register)
    }

    override suspend fun confirmEmail(
        confirmationEmail: ConfirmationEmail
    ): Response<String> {
        return api.confirmEmail(confirmationEmail)
    }

    override suspend fun submitRegistrationDetails(
        token: String,
        registrationDetails: RegistrationDetails
    ): Response<String> {
        return api.submitRegistrationDetails(token, registrationDetails)
    }

    override suspend fun submitMajorsList(
        token: String,
        majors: Majors
    ): Response<String> {
        return api.submitMajorsList(token, majors)
    }
}
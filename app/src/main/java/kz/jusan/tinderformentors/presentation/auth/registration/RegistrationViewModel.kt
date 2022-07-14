package kz.jusan.tinderformentors.presentation.auth.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kz.jusan.tinderformentors.data.remote.TinderForMentorsApi
import kz.jusan.tinderformentors.domain.entitites.ConfirmationEmail
import kz.jusan.tinderformentors.domain.entitites.Majors
import kz.jusan.tinderformentors.domain.entitites.Register
import kz.jusan.tinderformentors.domain.entitites.RegistrationDetails
import kz.jusan.tinderformentors.util.DispatcherProvider
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val api: TinderForMentorsApi,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    val successfulRegistration: MutableLiveData<Boolean> = MutableLiveData()
    val successfulEmailConfirmation: MutableLiveData<Boolean> = MutableLiveData()
    val successfulRegistrationDetails: MutableLiveData<Boolean> = MutableLiveData()
    var registrationMessage: String = ""
    var confirmEmailMessage: String = ""
    var registrationDetailsMessage: String = ""

    fun register(register: Register, confirmationEmail: ConfirmationEmail) {
        val registrationJob = viewModelScope.launch(dispatchers.io) {
            val registrationResponse = api.register(register)
            registrationMessage = registrationResponse.body().toString()
            if (registrationMessage.contains("Account was successfully registered")) {
                successfulRegistration.postValue(true)
            } else {
                successfulRegistration.postValue(false)
            }
        }

        runBlocking {
            registrationJob.join()
        }

        sendEmailVerification(confirmationEmail)
    }

    private fun sendEmailVerification(confirmationEmail: ConfirmationEmail) {
        viewModelScope.launch(dispatchers.io) {
            val confirmEmailResponse = api.confirmEmail(confirmationEmail)
            confirmEmailMessage = confirmEmailResponse.body().toString()
            if (confirmEmailMessage.contains("Registration verification email was sent")) {
                successfulEmailConfirmation.postValue(true)
            } else {
                successfulEmailConfirmation.postValue(false)
            }
        }
    }

    fun submitRegistrationDetails(token: String, registrationDetails: RegistrationDetails) {
        viewModelScope.launch(dispatchers.io) {
            val registrationDetailsResponse =
                api.submitRegistrationDetails(token, registrationDetails)
            registrationDetailsMessage = registrationDetailsResponse.body().toString()
            if (registrationDetailsMessage.contains("Form was successfully filled")) {
                successfulRegistrationDetails.postValue(true)
            } else {
                successfulRegistrationDetails.postValue(false)
            }
        }
    }

    fun submitMajorsList(token: String, majors: Majors) {
        viewModelScope.launch(dispatchers.io) {
            val submitMajorsListResponse = api.submitMajorsList(token, majors)
        }
    }

}
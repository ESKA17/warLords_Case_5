package kz.jusan.tinderformentors.presentation.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.jusan.tinderformentors.data.remote.TinderForMentorsApi
import kz.jusan.tinderformentors.domain.entitites.Login
import kz.jusan.tinderformentors.util.DispatcherProvider
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: TinderForMentorsApi,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var authToken: String

    fun login(login: Login) {
        viewModelScope.launch(dispatchers.io) {
            val loginResponse = api.login(login)
            authToken = loginResponse.body().toString()
            if (loginResponse.isSuccessful) {
                successful.postValue(true)
            } else {
                successful.postValue(false)
            }
        }
    }

}
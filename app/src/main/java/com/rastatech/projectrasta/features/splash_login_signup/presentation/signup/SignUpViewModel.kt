package com.rastatech.projectrasta.features.splash_login_signup.presentation.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.InvalidUserException
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO

import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.UserUseCases
import com.rastatech.projectrasta.ui.theme.Surface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

const val TAG = "SignUpViewModel"

/**
 * TODO for this Kotlin File
 *  - Please Add logic to password
 *
 * @property userUseCases
 */

@HiltViewModel
class SignUpViewModel @Inject constructor(

    private val userUseCases: UserUseCases

):ViewModel(){

    //For Visibility of Loading Screen
    private val _isLoading = mutableStateOf(false)
    val isLoading : State<Boolean>
    get() = _isLoading

    private val _error = mutableStateOf("")
    val error : State<String>
        get() = _error

    private val _hasError = mutableStateOf(false)
    val hasError : State<Boolean>
        get() = _hasError

    private val _navigateToLogin = mutableStateOf(false)
    val navigateToLogin : State<Boolean>
        get() = _navigateToLogin

    //This will be move to SignUpViewModel
    val firstName = mutableStateOf(TextFieldValue())

    val lastName =  mutableStateOf(TextFieldValue())
    val userName = mutableStateOf(TextFieldValue())
    val phoneNumber = mutableStateOf(TextFieldValue())
    val email =  mutableStateOf(TextFieldValue())

    val password =  mutableStateOf(TextFieldValue()) // This field is not stored and will be sent only to the API
    val verifyPassword = mutableStateOf(TextFieldValue())

    ///////

     fun onEvent(event: SignUpEvents){ // This event is supposedly not suspend. I just put suspend to prevent error for add user


        when (event){
            is SignUpEvents.AddUser -> {
                viewModelScope.launch(Dispatchers.IO) {

                    _isLoading.value = true

                    try {

                         val response = userUseCases.addUserApiRequest( // add user UseCase
                            user = UserRequestDTO(
                               email = email.value.text,
                               first_name = firstName.value.text ,
                               last_name = lastName.value.text,
                               password = password.value.text,
                               phone_number = phoneNumber.value.text,
                               username = userName.value.text,
                           )
                        )

                        Log.i(TAG, "Added ${userName.value.text} to Database")

                    } catch (e: InvalidUserException) {

                        Log.e(TAG,"An Error Occurred, Cannot Add User.")

                    } catch (e: Exception){

                        Log.i(TAG, e.message.toString())
                        _error.value = e.message.toString()
                        _hasError.value = true
                    }
                    finally {
                        delay(1000L)

                        _navigateToLogin.value = true // Mali ito

                        _error.value = ""
                        _hasError.value = false
                        _isLoading.value = false

                    }

                }

            }
        }
    }

    fun checkPassword() : Boolean{

        return true

    }

    fun checkEmail(): Boolean{
        return true
    }

}
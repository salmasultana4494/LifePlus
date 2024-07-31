package com.example.test.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.database.AppDAO
import com.example.test.models.RegistrationData
import kotlinx.coroutines.launch

class RegistrationViewModel(private val userDao: AppDAO) : ViewModel() {

    private val _registrationResult = MutableLiveData<Boolean>()
    val registrationResult: LiveData<Boolean> get() = _registrationResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun register(name: String, userName: String, password: String, phoneNumber: String) {
        if (validateInput(name, userName, password, phoneNumber)) {
            val registrationData = RegistrationData(
                name = name,
                userName = userName,
                password = password,
                phoneNumber = phoneNumber
            )
            viewModelScope.launch {
                userDao.insertDataRegistration(registrationData)
                _registrationResult.value = true
            }
        }
    }

    private fun validateInput(name: String, userName: String, password: String, phoneNumber: String): Boolean {
        return when {
            name.isEmpty() -> {
                _errorMessage.value = "Name is required"
                false
            }
            userName.isEmpty() -> {
                _errorMessage.value = "Username is required"
                false
            }
            password.isEmpty() -> {
                _errorMessage.value = "Password is required"
                false
            }
            phoneNumber.isEmpty() -> {
                _errorMessage.value = "Phone number is required"
                false
            }
            !phoneNumber.matches("\\d{10}".toRegex()) -> {
                _errorMessage.value = "Phone number must be 10 digits"
                false
            }
            else -> true
        }
    }
}
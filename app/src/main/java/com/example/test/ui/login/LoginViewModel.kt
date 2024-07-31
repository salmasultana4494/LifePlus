package com.example.test.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.database.AppDAO
import com.example.test.models.RegistrationData
import kotlinx.coroutines.launch

class LoginViewModel(private val userDao: AppDAO) : ViewModel() {

    private val _loginResult = MutableLiveData<RegistrationData?>()
    val loginResult: LiveData<RegistrationData?> get() = _loginResult

    private val _loginError = MutableLiveData<String?>()
    val loginError: LiveData<String?> get() = _loginError

    fun login(userName: String, password: String) {
        if (userName.isEmpty()) {
            _loginError.value = "Username is required"
            return
        }
        if (password.isEmpty()) {
            _loginError.value = "Password is required"
            return
        }

        viewModelScope.launch {
            val user = userDao.findUserByCredentials(userName, password)
            if (user != null) {
                _loginResult.value = user
            } else {
                _loginError.value = "Invalid credentials"
            }
        }
    }
}
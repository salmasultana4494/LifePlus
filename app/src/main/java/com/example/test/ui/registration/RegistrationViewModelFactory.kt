package com.example.test.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.database.AppDAO

class RegistrationViewModelFactory(private val userDao: AppDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
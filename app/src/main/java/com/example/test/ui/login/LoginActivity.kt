package com.example.test.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.test.database.AppDatabase
import com.example.test.databinding.ActivityLoginBinding
import com.example.test.ui.registration.RegistrationActivity
import com.example.test.ui.dashboard.DashboardActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "app_database"
            ).build().appDAO()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registrationTv.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val userName = binding.inputUserName.text.toString()
            val password = binding.inputPassword.text.toString()
            loginViewModel.login(userName, password)
        }

        loginViewModel.loginResult.observe(this) { user ->
            user?.let {
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("USER_DATA", user)
                startActivity(intent)
                finish()
            }
        }

        loginViewModel.loginError.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
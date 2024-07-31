package com.example.test.ui.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.test.database.AppDatabase
import com.example.test.databinding.ActivityRegistrationBinding
import com.example.test.ui.dashboard.DashboardActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private val registrationViewModel: RegistrationViewModel by viewModels {
        RegistrationViewModelFactory(
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "app_database"
            ).build().appDAO()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registrationBtn.setOnClickListener {
            val name = binding.inputName.text.toString()
            val userName = binding.inputUserName.text.toString()
            val password = binding.inputPassword.text.toString()
            val phoneNumber = binding.inputPhone.text.toString()

            registrationViewModel.register(name, userName, password, phoneNumber)
        }

        registrationViewModel.registrationResult.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        registrationViewModel.errorMessage.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

    }
}
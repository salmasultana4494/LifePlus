package com.example.test.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test.R
import com.example.test.databinding.ActivityProfileBinding
import com.example.test.models.RegistrationData

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val registrationData = intent.getParcelableExtra<RegistrationData>("USER_DATA")
        if (registrationData != null) {
            binding.userName.text = "Name: " + registrationData.name ?: "N/A"
            binding.userId.text = "User Name: " + registrationData.userName ?: "N/A"
            binding.userPhoneNo.text = "Phone Number:" + registrationData.phoneNumber ?: "N/A"
        }

        binding.goBack.setOnClickListener {
            finish()
        }
    }
}
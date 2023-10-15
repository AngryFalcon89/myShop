package com.example.myshop.screen.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myshop.R
import com.example.myshop.databinding.ActivitySignupLoginBinding
import androidx.fragment.app.Fragment

class SignupLoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignupLoginBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

}

package com.example.myshop.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myshop.R
import com.example.myshop.databinding.ActivityMainBinding
import com.example.myshop.screen.auth.SignupLoginActivity
import com.example.myshop.screen.home.HomeActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.getStarted.setOnClickListener {
            if(Firebase.auth.currentUser == null) {
                startActivity(Intent(this@MainActivity, SignupLoginActivity::class.java))
                finish()
            }else {
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                finish()
            }
        }
    }
}
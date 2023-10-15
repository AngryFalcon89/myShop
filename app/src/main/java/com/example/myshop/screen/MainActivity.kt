package com.example.myshop.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myshop.R
import com.example.myshop.databinding.ActivityMainBinding
import com.example.myshop.screen.auth.SignupLoginActivity

class MainActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.getStarted.setOnClickListener {
            var intent = Intent(this@MainActivity, SignupLoginActivity::class.java)
            startActivity(intent)
        }
    }
}
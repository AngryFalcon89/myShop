package com.example.myshop.screen.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myshop.R
import com.example.myshop.databinding.FragmentSuccessBinding
import com.example.myshop.screen.home.HomeActivity

class success : Fragment() {

    val binding by lazy{
        FragmentSuccessBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.done.setOnClickListener{
            startActivity(Intent(context, HomeActivity::class.java))
            activity?.finish()
        }
        return binding.root
    }

}
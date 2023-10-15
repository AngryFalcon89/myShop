package com.example.myshop.screen.auth


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myshop.R
import com.example.myshop.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
//    lateinit var binding: FragmentLoginBinding
    private val binding by lazy{
        FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.sSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment2)
        }



        return binding.root

    }
}

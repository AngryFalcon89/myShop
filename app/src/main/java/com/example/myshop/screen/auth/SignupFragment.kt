package com.example.myshop.screen.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myshop.R
import com.example.myshop.databinding.FragmentSignupBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupFragment : Fragment() {
private val binding by lazy{
    FragmentSignupBinding.inflate(layoutInflater)
}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.sLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment2_to_loginFragment)
        }

        binding.signup.setOnClickListener{

            Firebase.auth.createUserWithEmailAndPassword(
                binding.mEmail.text.toString(),
                binding.mPassword.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    findNavController().navigate(R.id.action_signupFragment2_to_success)
                }else{
                    Toast.makeText(
                        context,
                        it.exception?.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }
        return binding.root
    }
}

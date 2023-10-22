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
import com.google.firebase.database.ktx.database
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

            binding.textView.visibility = View.GONE
            binding.firstName.visibility = View.GONE
            binding.lastName.visibility = View.GONE
            binding.mEmail.visibility = View.GONE
            binding.mPassword.visibility = View.GONE
            binding.confirmPassword.visibility = View.GONE
            binding.sLogin.visibility = View.GONE
            binding.signup.visibility = View.GONE
            binding.textView2.visibility = View.GONE
            binding.view.visibility = View.GONE
            binding.view2.visibility = View.GONE
            binding.textView3.visibility = View.GONE
            binding.facebook.visibility = View.GONE
            binding.google.visibility = View.GONE
            binding.spinKit.visibility = View.VISIBLE
            binding.background.setBackgroundColor(resources.getColor(R.color.grey))


            Firebase.auth.createUserWithEmailAndPassword(
                binding.mEmail.text.toString(),
                binding.mPassword.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){

                    var userModel = UserModel(binding.firstName.text.toString(),
                        binding.lastName.text.toString(),
                        binding.mEmail.text.toString(),
                        binding.mPassword.text.toString()
                        )

                    Firebase
                        .database
                        .reference
                        .child("Users")
                        .child(it.result.user!!.uid)
                        .setValue(userModel)
                        .addOnSuccessListener{
                            findNavController().navigate(R.id.action_signupFragment2_to_success)
                        }
                        .addOnFailureListener{
                            Toast.makeText(context,it.localizedMessage,Toast.LENGTH_SHORT).show()
                        }
                }else{
                    binding.textView.visibility = View.VISIBLE
                    binding.firstName.visibility = View.VISIBLE
                    binding.lastName.visibility = View.VISIBLE
                    binding.mEmail.visibility = View.VISIBLE
                    binding.mPassword.visibility = View.VISIBLE
                    binding.confirmPassword.visibility = View.VISIBLE
                    binding.sLogin.visibility = View.VISIBLE
                    binding.signup.visibility = View.VISIBLE
                    binding.textView2.visibility = View.VISIBLE
                    binding.view.visibility = View.VISIBLE
                    binding.view2.visibility = View.VISIBLE
                    binding.textView3.visibility = View.VISIBLE
                    binding.facebook.visibility = View.VISIBLE
                    binding.google.visibility = View.VISIBLE
                    binding.spinKit.visibility = View.GONE
                    binding.background.setBackgroundColor(resources.getColor(R.color.white))
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

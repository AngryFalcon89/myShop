package com.example.myshop.screen.auth


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myshop.R
import com.example.myshop.databinding.FragmentLoginBinding
import com.example.myshop.screen.MainActivity
import com.example.myshop.screen.home.HomeActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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

        binding.login.setOnClickListener{

            var email = binding.email.text.toString()
            var password = binding.password.text.toString()

            if(email.isNotBlank()&&password.isNotBlank()) {
                binding.login.visibility = View.GONE
                binding.sSignup.visibility = View.GONE
                binding.google.visibility = View.GONE
                binding.view.visibility = View.GONE
                binding.email.visibility = View.GONE
                binding.facebook.visibility = View.GONE
                binding.forgetPassword.visibility = View.GONE
                binding.textView.visibility = View.GONE
                binding.textView2.visibility = View.GONE
                binding.textView3.visibility = View.GONE
                binding.view2.visibility = View.GONE
                binding.spinKit.visibility = View.VISIBLE
                binding.password.visibility = View.GONE
                binding.back.setBackgroundColor(resources.getColor(R.color.grey))

                Firebase
                    .auth
                    .signInWithEmailAndPassword(
                        binding.email.text.toString().trim(),
                        binding.password.text.toString().trim()
                    ).addOnSuccessListener {
                        Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
                        startActivity(Intent(context, HomeActivity::class.java))
                        activity?.finish()
                    }.addOnFailureListener{
                        binding.login.visibility = View.VISIBLE
                        binding.sSignup.visibility = View.VISIBLE
                        binding.google.visibility = View.VISIBLE
                        binding.view.visibility = View.VISIBLE
                        binding.email.visibility = View.VISIBLE
                        binding.facebook.visibility = View.VISIBLE
                        binding.forgetPassword.visibility = View.VISIBLE
                        binding.textView.visibility = View.VISIBLE
                        binding.textView2.visibility = View.VISIBLE
                        binding.textView3.visibility = View.VISIBLE
                        binding.view2.visibility = View.VISIBLE
                        binding.spinKit.visibility = View.GONE
                        binding.password.visibility = View.VISIBLE
                        binding.back.setBackgroundColor(resources.getColor(R.color.white))
                        Toast.makeText(context,it.localizedMessage,Toast.LENGTH_SHORT).show()
                    }
            }
        }



        return binding.root

    }
}

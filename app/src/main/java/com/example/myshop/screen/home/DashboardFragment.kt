package com.example.myshop.screen.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myshop.R
import com.example.myshop.databinding.FragmentDashboardBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects

class DashboardFragment : Fragment() {
    val binding by lazy{
        FragmentDashboardBinding.inflate(layoutInflater)
    }
    private lateinit var productList: ArrayList<ProductModel>
    private lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productList = ArrayList()

        getProductData()
        adapter = ProductAdapter(requireContext(),productList)
        binding.mainRv.adapter = adapter
    }

    private fun getProductData() {

        Firebase
            .firestore
            .collection("Products")
            .limit(10)
            .get()
            .addOnSuccessListener {
                productList.clear()
                for(i in it.documents){
                    var tempProductModel = i.toObject<ProductModel>()
                    tempProductModel?.id = i.id
                    productList.add(tempProductModel!!)
                }
                Log.d("result",productList.toString())
                adapter = ProductAdapter(requireContext(), productList)
                binding.mainRv.adapter = adapter
            }
    }

    companion object {

    }
}
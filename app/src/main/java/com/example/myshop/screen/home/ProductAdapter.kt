package com.example.myshop.screen.home

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myshop.R
import com.example.myshop.databinding.DashboardSingleItemBinding
import com.example.myshop.screen.detail.DetailActivity

class ProductAdapter(var context : Context, var productList: ArrayList<ProductModel>) :RecyclerView.Adapter<ProductAdapter.ViewHolder>(){
    inner class ViewHolder(var binding: DashboardSingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = DashboardSingleItemBinding.inflate(LayoutInflater.from(context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        val binding = holder.binding

        binding.productImage.load(product.imageUrl) {
            placeholder(R.drawable.preview)
        }
        binding.productName.text = product.name

        if (product.productCode.isNullOrEmpty()) {
            binding.productCode.visibility = View.GONE
        } else {
            binding.productCode.visibility = View.VISIBLE
            binding.productCode.text = product.productCode
        }

        if (product.discountedPrice == -1.0) {
            val actualPrice = product.price.toInt()
            binding.productPrice.visibility = View.GONE
            binding.productActualPrice.text = "Rs. ${actualPrice}"
            binding.productPercentageDiscount.visibility = View.GONE
        } else {
            val discountedPrice = product.discountedPrice.toInt()
            val actualPrice = product.price.toInt()
            binding.productPrice.text = "₹ ${discountedPrice}"
            binding.productActualPrice.text = "₹ ${actualPrice}"
            // Calculate and set the percentage discount
            val discountPercentage = calculatePercentageDiscount(product.price, product.discountedPrice)
            binding.productPercentageDiscount.text = "-${discountPercentage}% off"
            binding.productActualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        holder.itemView.setOnClickListener{
            context.startActivity(
                Intent(context, DetailActivity::class.java).putExtra(
                    "PRODUCT_ID",
                    productList.get(position).id
                )
            )
        }
    }

    private fun calculatePercentageDiscount(actualPrice: Double, salePrice: Double): Int {
        return if (actualPrice > 0) {
            ((1 - salePrice / actualPrice) * 100).toInt()
        } else {
            0
        }
    }

}
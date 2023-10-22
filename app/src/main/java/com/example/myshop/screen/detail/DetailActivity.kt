package com.example.myshop.screen.detail

import android.graphics.Paint
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.myshop.R
import com.example.myshop.databinding.ActivityDetailBinding
import com.example.myshop.screen.home.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class DetailActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    var product = ProductModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        var productId = intent.getStringExtra("PRODUCT_ID")

        Firebase
            .firestore
            .collection("Products")
            .document(productId!!)
            .get()
            .addOnSuccessListener {
                product = it.toObject<ProductModel>()!!
                product.id = it.id
                binding.expandedImage.load(product.imageUrl){
                    placeholder(R.drawable.preview)
                }
                binding.productTitle.text = product.name

                if (product.discountedPrice == -1.0) {
                    val actualPrice = product.price.toInt()
                    binding.discountedPrice.visibility = View.GONE
                    binding.actualPrice.text = "Rs. ${actualPrice}"
                    binding.percentageDiscount.visibility = View.GONE
                } else {
                    val discountedPrice = product.discountedPrice.toInt()
                    val actualPrice = product.price.toInt()
                    binding.discountedPrice.text = "₹ ${discountedPrice}"
                    binding.actualPrice.text = "₹ ${actualPrice}"
                    // Calculate and set the percentage discount
                    val discountPercentage = calculatePercentageDiscount(product.price, product.discountedPrice)
                    binding.percentageDiscount.text = "-${discountPercentage}% off"
                    binding.actualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }

        }

        // Set up the toolbar
        setSupportActionBar(binding.toolbar)

        // Enable the Up button (back button)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(false)
        actionBar?.setDisplayShowTitleEnabled(false);

        // Customize the Up button icon (optional)
        actionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.baseline_keyboard_backspace_24))

        // Handle Up button click (optional)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Add parallax and fade effect
        binding.expandedImage.alpha = 1.0f // Initial alpha value

        val productTitle = binding.productTitle // Assuming product_title is the correct ID
        binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalScrollRange = appBarLayout.totalScrollRange
            val alpha = (verticalOffset.toFloat() / totalScrollRange.toFloat()) + 1

            // Update the product title color based on the scroll position
            if (alpha >= 0.5) {
                actionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.baseline_keyboard_backspace_24))
                val titleColor = ContextCompat.getColor(this, R.color.white)
                binding.productTitle.setTextColor(titleColor)
            } else {
                actionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.baseline_arrow_back_ios_new_24))
                val titleColor = ContextCompat.getColor(this, R.color.black)
                binding.productTitle.setTextColor(titleColor)
            }

            binding.expandedImage.alpha = alpha
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
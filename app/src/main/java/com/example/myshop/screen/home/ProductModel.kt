package com.example.myshop.screen.home

class ProductModel {
    var id: String = ""
    var name: String = ""
    var price: Double = -1.0
    var description: String = ""
    var imageUrl: String = ""
    var productCode: String = ""
    var discountedPrice: Double = -1.0

    constructor(id: String, name: String, price: Double, description: String, imageUrl: String, productCode: String, discountedPrice: Double) {
        this.name = name
        this.price = price
        this.description = description
        this.imageUrl = imageUrl
        this.productCode = productCode
        this.discountedPrice = discountedPrice
    }

    constructor()
}
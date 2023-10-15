package com.example.myshop.screen.auth

class UserModel {
    var firstName : String = ""
    var lastName : String = ""
    var email : String = ""
    var password : String = ""

    constructor()

    constructor(firstName: String, lastName: String, email: String, password: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }
}
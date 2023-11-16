package com.example.ptslim.database

object UserMethods {

    fun login_user(email: String, password:String): String{
        return "Select userid from users where email = '$email' and password = '$password'"
    }

    fun register_user(firstName: String, lastName: String, email: String, password: String): String{
        return "call add_user('$firstName', '$lastName', '$email','$password');"
    }
}
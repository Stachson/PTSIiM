package com.example.ptslim.database

object DoctorMethods {

    fun add_doctor(firstName: String, lastName: String, speciality: String, description: String): String{
        return "add_user('$firstName', '$lastName', '$speciality', '$description')"
    }

    fun get_doctors(): String{
        return "Select * from doctors;"
    }

    fun get_description(firstName: String, lastName: String, speciality: String): String{
        return "Select description from doctors where firstname = '$firstName' and lastname = '$lastName' and speciality = '$speciality';"
    }

    fun get_specialities(): String{
        return "Select distinct speciality from doctors;"
    }
}
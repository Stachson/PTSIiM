package com.example.ptslim.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.ptslim.R
import com.example.ptslim.ViewModels.RegisterActivityViewModel

class RegisterActivity : AppCompatActivity() {

    lateinit var registerActivityViewModel: RegisterActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerActivityViewModel = RegisterActivityViewModel()
    }

    fun onClickRegisterUser(view: View){
        var email = findViewById<EditText>(R.id.r_email_entry).text.toString()
        var name = findViewById<EditText>(R.id.r_name_entry).text.toString()
        var lastName = findViewById<EditText>(R.id.r_last_name_entry).text.toString()
        var password = findViewById<EditText>(R.id.r_password_entry).text.toString()
        var password_again = findViewById<EditText>(R.id.r_password_again_entry).text.toString()

        if(email.isNotEmpty() && name.isNotEmpty() && lastName.isNotEmpty() && password.isNotEmpty() && password_again.isNotEmpty()){
            if(password == password_again){
                println("Wartosc done przed wywołaniem metody: ${registerActivityViewModel.done}")
                registerActivityViewModel.register_user(email, name, lastName, password)
                println("Wartosc done po wywołaniu metody: ${registerActivityViewModel.done}")
                if(registerActivityViewModel.done){
                    val toast = Toast.makeText(this,"Zarejestrowano użytkownika", Toast.LENGTH_LONG)
                    toast.show()
                    registerActivityViewModel.done = false
                } else{
                    val toast = Toast.makeText(this,"Użytkownik o podanym adresie email już istnieje", Toast.LENGTH_LONG)
                    toast.show()
                }
            } else{
                val toast = Toast.makeText(this, "Podane hasła nie są takie same", Toast.LENGTH_LONG)
                toast.show()
            }
        } else{
            val toast = Toast.makeText(this, "Nie podano wszystkich danych", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
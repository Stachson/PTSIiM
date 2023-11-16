package com.example.ptslim.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.ptslim.R
import com.example.ptslim.ViewModels.LoginActivityViewModel
import com.example.ptslim.database.GlobalData

class LoginActivity : AppCompatActivity() {

    lateinit var loginActivityViewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginActivityViewModel = LoginActivityViewModel()
    }

    fun onClickLogUser(view: View) {
        val email = findViewById<EditText>(R.id.email_entry).text.toString()
        val password = findViewById<EditText>(R.id.password_entry).text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
        loginActivityViewModel.login(email, password)
            if (GlobalData.sharedLogged) {
                val toast = Toast.makeText(this, "Zalogowano", Toast.LENGTH_LONG)
                toast.show()
            } else {
                val toast = Toast.makeText(this, "Niepoprawne dane logowania", Toast.LENGTH_LONG)
                toast.show()
            }
        } else{
            val toast = Toast.makeText(this, "Nie podano wszystkich danych logowania", Toast.LENGTH_LONG)
            toast.show()
        }
    }

    fun onClickRegisterIntent(view: View) {
        Intent(this, RegisterActivity::class.java).also {
            startActivity(it)
        }
    }
}
package com.example.ptslim.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ptslim.R
import com.example.ptslim.database.GlobalData


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickLogin(view: View){
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
        }
    }

    fun onClickDoctors(view: View){
        Intent(this, DoctorsActivity::class.java).also {
            startActivity(it)
        }
    }

    fun onClickFreeVisits(view: View){
        if(GlobalData.sharedLogged) {
            Intent(this, FreeVisitsActivity::class.java).also {
                startActivity(it)
            }
        } else {
            val toast = Toast.makeText(this,"Wymaga zalogowania", Toast.LENGTH_LONG)
            toast.show()
        }
    }

    fun onClickVisitsHistory(view: View){
        if(GlobalData.sharedLogged) {

            val intent = Intent(this, VisitsHistoryActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            //Intent(this, VisitsHistoryActivity::class.java).also {
            //  startActivity(it)
        } else {
            val toast = Toast.makeText(this,"Wymaga zalogowania", Toast.LENGTH_LONG)
            toast.show()
        }
    }

    fun onClickFutureVisits(view: View){
        if(GlobalData.sharedLogged) {
            Intent(this, FutureVisitsActivity::class.java).also {
                startActivity(it)
            }
        } else {
            val toast = Toast.makeText(this,"Wymaga zalogowania", Toast.LENGTH_LONG)
            toast.show()
        }
    }

}


package com.example.ptslim.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.ptslim.R
import com.example.ptslim.ViewModels.DoctorDetailActivityViewModel
import com.example.ptslim.ViewModels.DoctorsActivityViewModel
import com.example.ptslim.models.Doctor

class DoctorDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_detail)

        val doctorDetailActivityViewModel = DoctorDetailActivityViewModel()

        var doctorImage = findViewById<ImageView>(R.id.doctor_detai_image)
        doctorImage.setImageResource(R.drawable.doctor_img)
        var doctorName = findViewById<TextView>(R.id.doctor_detail_name)
        var doctorLastName = findViewById<TextView>(R.id.doctor_detail_last_name)
        var doctorSpeciality = findViewById<TextView>(R.id.doctor_detail_speciality)
        var doctorDescription = findViewById<TextView>(R.id.doctor_detail_description)

        val bundle: Bundle? = intent.extras
        doctorName.text = bundle?.getString("EXTRA_NAME")
        doctorLastName.text = bundle?.getString("EXTRA_LAST_NAME")
        doctorSpeciality.text = bundle?.getString("EXTRA_SPECIALITY")
        var name = bundle?.getString("EXTRA_NAME")
        var lastName = bundle?.getString("EXTRA_LAST_NAME")
        var speciality = bundle?.getString("EXTRA_SPECIALITY")
        if (name != null && lastName != null && speciality != null) {
            doctorDetailActivityViewModel.getDoctorDescription(name, lastName, speciality, object : DoctorDetailActivityViewModel.DataLoadListener {
                override fun onDataLoaded(description: String) {
                    runOnUiThread {
                        // Zaktualizuj interfejs użytkownika z opisem
                        doctorDescription.text = description
                    }
                }

                override fun onError(error: String) {
                    runOnUiThread {
                        // Obsługa błędu
                        println(error)
                    }
                }
            })
        }
    }
}





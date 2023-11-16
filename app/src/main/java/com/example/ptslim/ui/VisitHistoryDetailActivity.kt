package com.example.ptslim.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ptslim.R

class VisitHistoryDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visit_history_detail)

        var doctorName = findViewById<TextView>(R.id.visit_history_detail_doctor_name)
        var doctorLastName = findViewById<TextView>(R.id.visit_history_detail_doctor_last_name)
        var doctorSpeciality = findViewById<TextView>(R.id.visit_history_detail_doctor_speciality)
        var visitDate = findViewById<TextView>(R.id.visit_history_detail_day)
        var visitStartTime = findViewById<TextView>(R.id.visit_history_detail_starttime)
        var visitEndTime = findViewById<TextView>(R.id.visit_history_detail_endtime)
        var visitPrice = findViewById<TextView>(R.id.visit_history_detail_price)

        val bundle: Bundle? = intent.extras
        doctorName.text = bundle?.getString("EXTRA_NAME")
        doctorLastName.text = bundle?.getString("EXTRA_LAST_NAME")
        doctorSpeciality.text = bundle?.getString("EXTRA_SPECIALITY")
        visitDate.text = bundle?.getString("EXTRA_DATE")
        visitStartTime.text = bundle?.getString("EXTRA_START_TIME")?.dropLast(3)
        visitEndTime.text = bundle?.getString("EXTRA_END_TIME")?.dropLast(3)
        visitPrice.text = bundle?.getString("EXTRA_PRICE")
    }
}
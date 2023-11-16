package com.example.ptslim.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ptslim.R
import com.example.ptslim.ViewModels.DoctorsActivityViewModel
import com.example.ptslim.ViewModels.LoginActivityViewModel
import com.example.ptslim.adapters.DoctorListAdapter
import com.example.ptslim.models.Doctor
import com.example.ptslim.models.DoctorForList

class DoctorsActivity : AppCompatActivity() {

    lateinit var doctorsActivityViewModel: DoctorsActivityViewModel
    lateinit var newRecyclerView: RecyclerView
    lateinit var doctors_list: ArrayList<Doctor>
    lateinit var new_doctors_list: ArrayList<DoctorForList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors)

        doctors_list = arrayListOf<Doctor>()

        newRecyclerView = findViewById(R.id.recyclerViewDoctors)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        doctorsActivityViewModel = DoctorsActivityViewModel()
        new_doctors_list = arrayListOf<DoctorForList>()

        val adapter = DoctorListAdapter(new_doctors_list, object : DoctorListAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent: Intent = Intent(this@DoctorsActivity, DoctorDetailActivity::class.java)
                intent.putExtra("EXTRA_NAME", new_doctors_list[position].doctor_name)
                intent.putExtra("EXTRA_LAST_NAME", new_doctors_list[position].doctor_lastName)
                intent.putExtra("EXTRA_SPECIALITY", new_doctors_list[position].speciality)
                startActivity(intent)
            }
        })

        newRecyclerView.adapter = adapter

        doctorsActivityViewModel.getAllDoctors(object : DoctorsActivityViewModel.DataLoadListener {
            override fun onDataLoaded(doctors: ArrayList<Doctor>) {
                doctors_list = doctors
                getData()
                runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onError(error: String) {
                runOnUiThread {
                    println(error)
                }
            }

            fun getData() {
                for (i in doctors_list) {
                    val doctorForList =
                        DoctorForList(R.drawable.doctor_img, i.firstName, i.lastName, i.speciality)
                    new_doctors_list.add(doctorForList)
                }
            }
        })
    }
}

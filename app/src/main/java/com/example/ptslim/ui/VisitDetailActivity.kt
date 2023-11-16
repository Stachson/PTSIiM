package com.example.ptslim.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.ptslim.R
import com.example.ptslim.ViewModels.DoctorsActivityViewModel
import com.example.ptslim.ViewModels.VisitDetailActivityViewModel
import com.example.ptslim.models.Doctor
import com.example.ptslim.models.DoctorForList
import org.w3c.dom.Text

class VisitDetailActivity : AppCompatActivity() {

    lateinit var visitDetailActivityViewModel: VisitDetailActivityViewModel
    var visitID = 0

    lateinit var freeVisitsActivity: FreeVisitsActivity
    lateinit var futureVisitsActivity: FutureVisitsActivity
    lateinit var visitsHistoryActivity: VisitsHistoryActivity

    lateinit var methodName: String
    lateinit var methodLastName: String
    lateinit var methodDay: String
    //lateinit var methodStartTime: String
    //lateinit var methodEndTime: String


    lateinit var startTime: String
    lateinit var endTime: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visit_detail)

        freeVisitsActivity = FreeVisitsActivity()
        futureVisitsActivity = FutureVisitsActivity()
        visitsHistoryActivity = VisitsHistoryActivity()

        visitDetailActivityViewModel = VisitDetailActivityViewModel()

        var doctorName = findViewById<TextView>(R.id.visit_detail_doctor_name)
        var doctorLastName = findViewById<TextView>(R.id.visit_detail_doctor_last_name)
        var doctorSpeciality = findViewById<TextView>(R.id.visit_detail_doctor_speciality)
        var visitDate = findViewById<TextView>(R.id.visit_detail_day)
        var visitStartTime = findViewById<TextView>(R.id.visit_detail_starttime)
        var visitEndTime = findViewById<TextView>(R.id.visit_detail_endtime)
        var visitPrice = findViewById<TextView>(R.id.visit_detail_price)


        val bundle: Bundle? = intent.extras
        doctorName.text = bundle?.getString("EXTRA_NAME")
        methodName =  bundle?.getString("EXTRA_NAME").toString()
        doctorLastName.text = bundle?.getString("EXTRA_LAST_NAME")
        methodLastName = bundle?.getString("EXTRA_LAST_NAME").toString()
        doctorSpeciality.text = bundle?.getString("EXTRA_SPECIALITY")
        visitDate.text = bundle?.getString("EXTRA_DATE")
        methodDay = bundle?.getString("EXTRA_DATE").toString()
        visitStartTime.text = bundle?.getString("EXTRA_START_TIME")?.dropLast(3)
        startTime = bundle?.getString("EXTRA_START_TIME").toString()
        visitEndTime.text = bundle?.getString("EXTRA_END_TIME")?.dropLast(3)
        endTime = bundle?.getString("EXTRA_END_TIME").toString()
        visitPrice.text = bundle?.getString("EXTRA_PRICE")

        println(methodName)
        println(methodLastName)
        println(methodDay)
        println(startTime)
        println(endTime)
    }

    fun onClickRegisterVisit(view: View){
        visitDetailActivityViewModel.getVisitID(methodName, methodLastName, methodDay, startTime, endTime, object : VisitDetailActivityViewModel.DataLoadListener {
            override fun onDataLoaded(newVisitID: Int) {
                visitID = newVisitID
                visitDetailActivityViewModel.registerVisit(visitID)
                visitDetailActivityViewModel.addTovisitHistory(visitID)

                //finish()
                runOnUiThread {
                   // freeVisitsActivity.newRecyclerView.adapter?.notifyDataSetChanged()
                    //visitsHistoryActivity.newRecyclerView.adapter?.notifyDataSetChanged()
                    //futureVisitsActivity.newRecyclerView.adapter?.notifyDataSetChanged()
                }
                //println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA $visitID")
            }

            override fun onError(error: String) {
                runOnUiThread {
                    println(error)
                }
            }

            //fun getData() {
            //    for (i in doctors_list) {
            //        val doctorForList =
            //            DoctorForList(R.drawable.doctor_img, i.firstName, i.lastName, i.speciality)
            //        new_doctors_list.add(doctorForList)
            //    }
            //}
        })

    }
}
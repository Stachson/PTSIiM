package com.example.ptslim.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ptslim.R
import com.example.ptslim.ViewModels.DoctorsActivityViewModel
import com.example.ptslim.ViewModels.FreeVisitsActivityViewModel
import com.example.ptslim.adapters.DoctorListAdapter
import com.example.ptslim.adapters.FreeVisitsListAdapter
import com.example.ptslim.models.Doctor
import com.example.ptslim.models.DoctorForList
import com.example.ptslim.models.FreeVisit
import com.example.ptslim.models.Visit

class FreeVisitsActivity : AppCompatActivity() {

    lateinit var freeVisitsActivityViewModel: FreeVisitsActivityViewModel
    lateinit var newRecyclerView: RecyclerView
    lateinit var visitsList: ArrayList<FreeVisit>
    lateinit var newVisitsList: ArrayList<FreeVisit>
    lateinit var firstSpecialities: ArrayList<String>
    lateinit var newSpecialities: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_free_visits)

        visitsList = arrayListOf<FreeVisit>()
        newVisitsList = arrayListOf<FreeVisit>()
        firstSpecialities = arrayListOf<String>()
        newSpecialities = arrayListOf<String>()

        newRecyclerView = findViewById(R.id.recyclerViewFreeVisits)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        freeVisitsActivityViewModel = FreeVisitsActivityViewModel()

        val adapter = FreeVisitsListAdapter(newVisitsList, object : FreeVisitsListAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent: Intent = Intent(this@FreeVisitsActivity, VisitDetailActivity::class.java)
                intent.putExtra("EXTRA_NAME", newVisitsList[position].firstName)
                intent.putExtra("EXTRA_LAST_NAME", newVisitsList[position].lastName)
                intent.putExtra("EXTRA_SPECIALITY", newVisitsList[position].speciality)
                intent.putExtra("EXTRA_DATE", newVisitsList[position].day.toString())
                intent.putExtra("EXTRA_START_TIME", newVisitsList[position].starttime.toString())
                intent.putExtra("EXTRA_END_TIME", newVisitsList[position].endtime.toString())
                intent.putExtra("EXTRA_PRICE", newVisitsList[position].price.toString())
                startActivity(intent)

            }
        })

        newRecyclerView.adapter = adapter
        freeVisitsActivityViewModel.getFreeVisits(object : FreeVisitsActivityViewModel.DataLoadListener {
            override fun onDataLoaded(visits: ArrayList<FreeVisit>) {
                visitsList = visits
                getData()
                runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
//                println("AAAAAAAAAAAAA ${visitsList[1].firstName}")
            }


            override fun onError(error: String) {
                runOnUiThread {
                    println(error)
                }
            }

            fun getData() {
                for (i in visitsList) {
                    val freeVisit =
                        FreeVisit(i.firstName, i.lastName, i.speciality, i.day, i.starttime, i.endtime, i.roomNumber, i.price)
                    newVisitsList.add(freeVisit)
                }
            }
        })

        freeVisitsActivityViewModel.getDoctorSpecialities(object : FreeVisitsActivityViewModel.DataLoadListener1 {
            override fun onDataLoaded1(specialities: ArrayList<String>) {
                firstSpecialities = specialities
                getData()
                runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
                //println("AAAAAAAAAAAAA ${visitsList[1].firstName}")
            }


            override fun onError(error: String) {
                runOnUiThread {
                    println(error)
                }
            }

            fun getData() {
                newSpecialities.add("Wszystkie specjalizacje")
                for (i in firstSpecialities) {
                    newSpecialities.add(i)
                }
            }
        })


        val spinner: Spinner = findViewById(R.id.specialitySpinner)
        val dataFromDatabase = ArrayList<String>()
        //println(newSpecialities)
        //dataFromDatabase.addAll(newSpecialities)
        //println(dataFromDatabase)

        val adapterSpinner = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, newSpecialities)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterSpinner

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Obsługa wyboru pozycji
                val selectedOption = newSpecialities[position]
                spinner.setSelection(position)
                // Możesz wykorzystać selectedOption do dalszej obróbki
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                spinner.setSelection(0)
            }
        }





    }

    override fun onBackPressed() {
        finish()
    }



}
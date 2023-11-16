package com.example.ptslim.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ptslim.R
import com.example.ptslim.ViewModels.FreeVisitsActivityViewModel
import com.example.ptslim.ViewModels.VisitsHistoryViewModel
import com.example.ptslim.adapters.FreeVisitsListAdapter
import com.example.ptslim.models.FreeVisit

class VisitsHistoryActivity : AppCompatActivity() {

    lateinit var visitsHistoryViewModel: VisitsHistoryViewModel
    lateinit var newRecyclerView: RecyclerView
    lateinit var visitsList: ArrayList<FreeVisit>
    lateinit var newVisitsList: ArrayList<FreeVisit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visits_history)

        visitsList = arrayListOf<FreeVisit>()
        newVisitsList = arrayListOf<FreeVisit>()

        newRecyclerView = findViewById(R.id.recyclerViewVisitsHistory)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        visitsHistoryViewModel = VisitsHistoryViewModel()

        val adapter = FreeVisitsListAdapter(newVisitsList, object : FreeVisitsListAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent: Intent = Intent(this@VisitsHistoryActivity, VisitHistoryDetailActivity::class.java)
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
        println("TEST DOJŚCIA")
        visitsHistoryViewModel.getVisitsHistory(object : VisitsHistoryViewModel.DataLoadListener {
            override fun onDataLoaded(visits: ArrayList<FreeVisit>) {
                visitsList = visits
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
                for (i in visitsList) {
                    val freeVisit =
                        FreeVisit(i.firstName, i.lastName, i.speciality, i.day, i.starttime, i.endtime, i.roomNumber, i.price)
                    newVisitsList.add(freeVisit)
                }
            }
        })

    }

    override fun onBackPressed() {
        finish()
    }
}
package com.example.ptslim.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ptslim.R
import com.example.ptslim.models.DoctorForList
import com.example.ptslim.models.FreeVisit

class FreeVisitsListAdapter (private val visitsList: ArrayList<FreeVisit>, private val mListener: onItemClickListener):
    RecyclerView.Adapter<FreeVisitsListAdapter.MyViewHolder>() {

    //private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    //fun setOnClickListener(listener : onItemClickListener){
    //    mListener = listener
    //}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.free_visit_item,
            parent,false)
        return MyViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return visitsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = visitsList[position]
        holder.freeVisitDoctorName.text = currentItem.firstName.plus(" ").plus(currentItem.lastName)
        holder.freeVisitDoctorSpeciality.text = currentItem.speciality
        holder.freeVisitDate.text = currentItem.day.toString()
        holder.freeVisitStartTime.text = currentItem.starttime.toString().dropLast(3).plus(" -")
        holder.freeVisitEndTime.text = currentItem.endtime.toString().dropLast(3)
        holder.freeVisitPrice.text = currentItem.price.toString()
    }


    class MyViewHolder(itemView : View, listener : onItemClickListener) : RecyclerView.ViewHolder(itemView){
        var freeVisitDoctorName  : TextView = itemView.findViewById(R.id.freeVisitDoctorName)
        val freeVisitDoctorSpeciality : TextView = itemView.findViewById(R.id.freeVisitDoctorSpeciality)
        val freeVisitDate : TextView = itemView.findViewById(R.id.freeVisitDate)
        val freeVisitStartTime : TextView = itemView.findViewById(R.id.freeVisitStartTime)
        val freeVisitEndTime : TextView = itemView.findViewById(R.id.freeVisitEndTime)
        val freeVisitPrice : TextView = itemView.findViewById(R.id.freeVisitPrice)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}
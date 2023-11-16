package com.example.ptslim.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ptslim.R
import com.example.ptslim.models.DoctorForList
import com.google.android.material.imageview.ShapeableImageView

class DoctorListAdapter(private val doctorsList: ArrayList<DoctorForList>,private val mListener: onItemClickListener):
    RecyclerView.Adapter<DoctorListAdapter.MyViewHolder>() {

    //private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    //fun setOnClickListener(listener : onItemClickListener){
    //    mListener = listener
    //}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.doctor_list_item,
            parent,false)
        return MyViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return doctorsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = doctorsList[position]
        holder.doctorImage.setImageResource(R.drawable.doctor_img)
        holder.doctorName.text = currentItem.doctor_name.plus(" ").plus(currentItem.doctor_lastName)
        holder.doctorSpeciality.text = currentItem.speciality
    }


    class MyViewHolder(itemView : View, listener : onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val doctorImage  : ImageView = itemView.findViewById(R.id.doctor_image)
        val doctorName : TextView = itemView.findViewById(R.id.doctor_name)
        val doctorSpeciality : TextView = itemView.findViewById(R.id.doctor_speciality)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}
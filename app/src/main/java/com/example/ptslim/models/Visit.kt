package com.example.ptslim.models

import java.sql.Date
import java.sql.Time

data class Visit(
    val visitID: Int,
    val userID: Int,
    val doctorID: Int,
    val day: Date,
    val starttime: Time,
    val endtime: Time,
    val roomID: Int,
    val price: Float
)
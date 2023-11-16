package com.example.ptslim.models

import java.math.BigDecimal
import java.sql.Date
import java.sql.Time

data class FreeVisit (
    val firstName: String,
    val lastName: String,
    val speciality: String,
    val day: Date,
    val starttime: Time,
    val endtime: Time,
    val roomNumber: Int,
    val price: BigDecimal
)
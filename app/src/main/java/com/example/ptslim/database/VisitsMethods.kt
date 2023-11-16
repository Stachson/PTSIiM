package com.example.ptslim.database

import java.sql.Date
import java.sql.Time

object VisitsMethods {


    fun get_visit_id(firstName: String, lastName: String, date: Date, startTime: Time, endtime: Time): String{
        return "Select * from find_visit_id('$firstName', '$lastName', '$date', '$startTime', '$endtime');"
    }

    fun add_to_visit_history(id: Int): String{
        return "Select copy_visit_to_visit_history($id);"
    }

    fun get_all_free_visits(): String{
        return "Select * from get_all_free_visits();"
    }

    fun get_all_free_visits_on_date(date: String): String{
        return "select * from get_free_visits_on_date('$date'::date);"
    }

    fun get_all_free_visits_on_date_and_speciality(date: String, speciality: String): String{
        return "select * from get_free_visits_on_date_and_speciality('$date'::date, '$speciality');"
    }

    fun get_all_free_visits_by_speciality(speciality: String): String{
        return "select * from get_free_visits_by_speciality('$speciality');"
    }

    fun get_my_visits(userid: Int): String{
        return "select * from get_my_visits($userid);"
    }

    fun get_my_future_visits(userid: Int): String{
        return "select * from get_my_future_visits($userid);"
    }


}
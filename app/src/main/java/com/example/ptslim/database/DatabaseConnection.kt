package com.example.ptslim.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseConnection {
    fun connectToDatabase(callback: (Connection?) -> Unit) {
        Thread {
            val connection = doInBackground()
            callback(connection)
        }.start()
    }

    private fun doInBackground(): Connection? {
        try {
            Class.forName("org.postgresql.Driver") // Ładowanie sterownika JDBC
            val url = "jdbc:postgresql://database-1.cgeey5aj9gfu.eu-north-1.rds.amazonaws.com:5432/PTSLiMDatabase"
            val username = "student259142"
            val password = "Qwerty456!"
            return DriverManager.getConnection(url, username, password)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

}

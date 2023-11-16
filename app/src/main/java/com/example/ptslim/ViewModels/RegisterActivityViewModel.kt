package com.example.ptslim.ViewModels

import com.example.ptslim.database.DatabaseConnection
import com.example.ptslim.database.UserMethods

class RegisterActivityViewModel {

    var done = false

    fun register_user(email: String, name: String, lastName: String, password: String){
        Thread {
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    try {
                        val statement = connection.createStatement()
                        val query = UserMethods.register_user(name, lastName, email, password)
                        val rowsAffected = statement.executeUpdate(query)
                        statement.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        //done = false

                    } finally {
                        connection.close()
                    }
                } else {
                    // Obsługa błędu podczas nawiązywania połączenia
                }
            }
        }.start()
    }
}
package com.example.ptslim.ViewModels

import com.example.ptslim.database.DatabaseConnection
import com.example.ptslim.database.DoctorMethods
import com.example.ptslim.models.Doctor

class DoctorDetailActivityViewModel {

    interface DataLoadListener {
        fun onDataLoaded(description: String)
        fun onError(error: String)
    }

    fun getDoctorDescription( name: String, lastName: String, speciality: String, dataLoadListener: DataLoadListener){

        var description = ""
        Thread {
            // Wywołaj funkcję connectToDatabase z DatabaseConnection w osobnym wątku
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    // Połączenie nawiązane pomyślnie, możesz wykonać zapytanie SQL
                    try {
                        val statement = connection.createStatement()
                        val query = DoctorMethods.get_description(name, lastName, speciality)

                        val resultSet = statement.executeQuery(query)
                        while (resultSet.next()) {
                            description = resultSet.getString("description")
                        }
                        resultSet.close()
                        statement.close()
                        dataLoadListener.onDataLoaded(description)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        dataLoadListener.onError("Błąd podczas pobierania danych")
                    } finally {
                        connection.close()
                    }
                } else {
                    dataLoadListener.onError("Błąd podczas nawiązywania połączenia z bazą danych")

                }
            }
        }.start()
    }
}
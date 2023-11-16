package com.example.ptslim.ViewModels

import com.example.ptslim.database.DatabaseConnection
import com.example.ptslim.database.DoctorMethods
import com.example.ptslim.database.GlobalData
import com.example.ptslim.database.VisitsMethods
import com.example.ptslim.models.Doctor
import java.sql.Date
import java.sql.Time

class VisitDetailActivityViewModel {

    interface DataLoadListener {
        fun onDataLoaded(newVisitID: Int)
        fun onError(error: String)
    }

    fun getVisitID(doctorName: String, doctorLastName: String, visitDate: String, visitStartTime: String, visitEndTime: String, dataLoadListener: DataLoadListener) {
        Thread {
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    try {
                        val statement = connection.createStatement()
                        val query = VisitsMethods.get_visit_id(doctorName, doctorLastName, Date.valueOf(visitDate), Time.valueOf(visitStartTime), Time.valueOf(visitEndTime))
                        println(doctorName)
                        println(doctorLastName)
                        println(Date.valueOf(visitDate))
                        println(Time.valueOf(visitStartTime))
                        println(Time.valueOf(visitEndTime))
                        val resultSet = statement.executeQuery(query)

                        var newVisitID: Int = 0

                        while (resultSet.next()) {
                            newVisitID = resultSet.getInt(1)
                        }
                        resultSet.close()
                        statement.close()

                        // Po zakończeniu pobierania danych, przekaż je za pomocą callbacka
                        dataLoadListener.onDataLoaded(newVisitID)
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

    fun registerVisit(id: Int){
        Thread {
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    try {
                        val statement = connection.createStatement()

                        val query = "Update visits Set userid = '${GlobalData.sharedUserID}' Where visitid = '$id'"


                        val rowsAffected = statement.executeUpdate(query)

                        statement.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        // Upewnij się, że połączenie jest zawsze zamykane
                        connection.close()
                    }
                } else {
                    // Obsługa błędu podczas nawiązywania połączenia
                }
            }
        }.start()

    }

    fun addTovisitHistory(id: Int){
        Thread {
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    try {
                        val statement = connection.createStatement()

                        val query = VisitsMethods.add_to_visit_history(id)
                        val rowsAffected = statement.executeUpdate(query)

                        statement.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        // Upewnij się, że połączenie jest zawsze zamykane
                        connection.close()
                    }
                } else {
                    // Obsługa błędu podczas nawiązywania połączenia
                }
            }
        }.start()

    }
}
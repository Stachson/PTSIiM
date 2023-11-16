package com.example.ptslim.ViewModels

import com.example.ptslim.database.DatabaseConnection
import com.example.ptslim.database.DoctorMethods
import com.example.ptslim.database.VisitsMethods
import com.example.ptslim.models.Doctor
import com.example.ptslim.models.FreeVisit
import com.example.ptslim.models.Visit

class FreeVisitsActivityViewModel {

    interface DataLoadListener {
        fun onDataLoaded(freeVisits: ArrayList<FreeVisit>)
        fun onError(error: String)
    }

    interface DataLoadListener1{
        fun onDataLoaded1(specialities: ArrayList<String>)
        fun onError(error: String)
    }

    fun getFreeVisits(dataLoadListener: DataLoadListener) {
        Thread {
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    try {
                        val statement = connection.createStatement()
                        val query = VisitsMethods.get_all_free_visits()
                        val resultSet = statement.executeQuery(query)

                        val freeVisits: ArrayList<FreeVisit> = arrayListOf<FreeVisit>()

                        while (resultSet.next()) {
                            var visit = FreeVisit(
                                resultSet.getString("firstname"),
                                resultSet.getString("lastname"),
                                resultSet.getString("speciality"),
                                resultSet.getDate("day"),
                                resultSet.getTime("starttime"),
                                resultSet.getTime("endtime"),
                                resultSet.getInt("roomnumber"),
                                resultSet.getBigDecimal("price")
                            )
                            freeVisits.add(visit)
                            println(visit.firstName)
                        }
                        resultSet.close()
                        statement.close()

                        // Po zakończeniu pobierania danych, przekaż je za pomocą callbacka
                        dataLoadListener.onDataLoaded(freeVisits)
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

    fun getDoctorSpecialities(dataLoadListener: DataLoadListener1) {
        Thread {
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    try {
                        val statement = connection.createStatement()
                        val query = DoctorMethods.get_specialities()
                        val resultSet = statement.executeQuery(query)

                        val specialities: ArrayList<String> = arrayListOf<String>()

                        while (resultSet.next()) {
                            var speciality = resultSet.getString("speciality")
                            specialities.add(speciality)
                            //println(visit.firstName)
                        }
                        resultSet.close()
                        statement.close()

                        // Po zakończeniu pobierania danych, przekaż je za pomocą callbacka
                        dataLoadListener.onDataLoaded1(specialities)
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
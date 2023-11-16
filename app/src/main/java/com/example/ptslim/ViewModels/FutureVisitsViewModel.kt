package com.example.ptslim.ViewModels

import com.example.ptslim.database.DatabaseConnection
import com.example.ptslim.database.DoctorMethods
import com.example.ptslim.database.GlobalData
import com.example.ptslim.database.VisitsMethods
import com.example.ptslim.models.FreeVisit

class FutureVisitsViewModel {
    interface DataLoadListener {
        fun onDataLoaded(freeVisits: ArrayList<FreeVisit>)
        fun onError(error: String)
    }

    fun getFutureVisits(dataLoadListener: DataLoadListener) {
        Thread {
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    try {
                        val statement = connection.createStatement()
                        val query = VisitsMethods.get_my_future_visits(GlobalData.sharedUserID)
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
                                0,
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


}
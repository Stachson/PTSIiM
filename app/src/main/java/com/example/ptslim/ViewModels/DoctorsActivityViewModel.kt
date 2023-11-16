package com.example.ptslim.ViewModels

import com.example.ptslim.database.DatabaseConnection
import com.example.ptslim.database.DoctorMethods
import com.example.ptslim.models.Doctor

class DoctorsActivityViewModel {

    interface DataLoadListener {
        fun onDataLoaded(doctors: ArrayList<Doctor>)
        fun onError(error: String)
    }

    fun getAllDoctors(dataLoadListener: DataLoadListener) {
        Thread {
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    try {
                        val statement = connection.createStatement()
                        val query = DoctorMethods.get_doctors()
                        val resultSet = statement.executeQuery(query)

                        val doctors: ArrayList<Doctor> = arrayListOf<Doctor>()

                        while (resultSet.next()) {
                            var doctor = Doctor(
                                resultSet.getInt("doctorid"),
                                resultSet.getString("firstname"),
                                resultSet.getString("lastname"),
                                resultSet.getString("speciality"),
                                resultSet.getString("description")
                            )
                            doctors.add(doctor)
                        }
                        resultSet.close()
                        statement.close()

                        // Po zakończeniu pobierania danych, przekaż je za pomocą callbacka
                        dataLoadListener.onDataLoaded(doctors)
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

    /*
    fun getAllDoctors(): ArrayList<Doctor>{

        val doctors: ArrayList<Doctor> = arrayListOf<Doctor>()
        Thread {
            // Wywołaj funkcję connectToDatabase z DatabaseConnection w osobnym wątku
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    try {
                        val statement = connection.createStatement()
                        val query = DoctorMethods.get_doctors()
                        val resultSet = statement.executeQuery(query)

                        while (resultSet.next()) {
                            var doctor = Doctor(
                                resultSet.getInt("doctorid"),
                                resultSet.getString("firstname"),
                                resultSet.getString("lastname"),
                                resultSet.getString("speciality"),
                                resultSet.getString("description")
                            )
                            doctors.add(doctor)

                            println("${doctor.doctorID}  ${doctor.firstName} ${doctor.lastName}")
                        }
                        resultSet.close()
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
        return doctors
    }

     */
}
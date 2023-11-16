package com.example.ptslim.ViewModels

import com.example.ptslim.database.DatabaseConnection
import com.example.ptslim.database.UserMethods
import com.example.ptslim.database.GlobalData
import com.example.ptslim.ui.MainActivity
import kotlin.properties.Delegates

class LoginActivityViewModel {

    var userID by Delegates.notNull<Int>()
    var logged = false
    var mainActivity: MainActivity = MainActivity()


    fun login(email: String, password: String){

        Thread {
            // Wywołaj funkcję connectToDatabase z DatabaseConnection w osobnym wątku
            DatabaseConnection.connectToDatabase { connection ->
                if (connection != null) {
                    // Połączenie nawiązane pomyślnie, możesz wykonać zapytanie SQL
                    try {
                        val statement = connection.createStatement()
                        val query = UserMethods.login_user(email, password)
                        val resultSet = statement.executeQuery(query)
                        if(resultSet.next()) {
                            userID = resultSet.getString("userid").toInt()
                            GlobalData.sharedUserID = userID
                            logged = true
                            GlobalData.sharedLogged = true
                            println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa $userID")
                        }
                        resultSet.close()
                        statement.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
//                        throw Exception()
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
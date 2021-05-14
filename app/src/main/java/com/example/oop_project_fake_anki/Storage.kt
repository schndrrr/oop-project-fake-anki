package com.example.oop_project_fake_anki
import android.content.Context
import java.io.IOException

class Storage {

    fun getData(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
            return jsonString
        }
    }

    fun parseData(jsonString: String) {

    }


    fun saveData() {

    }

}
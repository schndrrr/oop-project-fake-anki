package com.example.oop_project_fake_anki.utility

import android.content.Context
import com.example.oop_project_fake_anki.classes.Stack
import java.io.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Storage {

    fun getData(context: Context, fileName: String): String {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return "null"
        }
        return jsonString
    }

   fun parseData(jsonString: String): List<Stack> {
       val gson = Gson()
       val testType = object : TypeToken<List<Stack>>() {}.type
       val stack: List<Stack> = gson.fromJson(jsonString, testType)
       return stack
   }
}
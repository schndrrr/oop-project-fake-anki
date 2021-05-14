package com.example.oop_project_fake_anki
import android.content.Context
import java.io.IOException

import androidx.core.content.ContextCompat
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer

class Storage {

    fun getData(context: Context, fileName: String): String {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return "null"
        }
        println(jsonString)
        return jsonString
    }

    fun parseData(jsonString: String) {

    }


    fun saveData(jsonString: String) {
        val output: Writer
        val file = createFile()
        output = BufferedWriter(FileWriter(file))
        output.write(jsonString)
        output.close()
    }

    fun createFile(): File {
        val fileName = "storage"
        //val storageDir = "src/main/assets"
       // if(!storageDir.ex()){
        //    storageDir.mkdir()
        //}
        return File.createTempFile(fileName, ".json", File("src/main/assets"))
    }

}
package com.example.oop_project_fake_anki.utility

import android.app.Service
import android.content.Intent
import android.os.IBinder

class StorageService : Service() {

    public var test: String = "hallo"

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
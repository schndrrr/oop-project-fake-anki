package com.example.oop_project_fake_anki.classes

class User constructor(name: String){
    val password: String = ""
    val isLoggedIn: Boolean = false

    fun login(enteredPassword: String){
        if (enteredPassword == this.password){
            isLoggedIn == true
        }
    }

    fun logout(){
        if (this.isLoggedIn==true){
//            irgendein redirect
        }
    }
}
package com.example.megroup9gmailcom.proekt

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(
    val name: String,
    @PrimaryKey
    val login: String,
    val pass: String,
    val email: String,
    val picture: String,
    val telephone: String
) {

    class List : ArrayList<User>()
}
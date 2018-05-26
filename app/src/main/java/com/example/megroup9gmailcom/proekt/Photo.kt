package com.example.megroup9gmailcom.proekt

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Photo(
    val title: String,
    @PrimaryKey
    val url: String,
    val thumbnailUrl: String
) {

    class List : ArrayList<Photo>()
}
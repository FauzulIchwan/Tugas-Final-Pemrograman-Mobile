package com.fauzul.KontakList.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "notelp")
    var notelp: String
)
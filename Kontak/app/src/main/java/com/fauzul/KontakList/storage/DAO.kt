package com.fauzul.KontakList.storage

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Query("SELECT * from contact ORDER BY id ASC")
    fun getContact(): LiveData<List<Contact>>

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)
}
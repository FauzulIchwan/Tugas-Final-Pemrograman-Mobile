package com.fauzul.KontakList.storage

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class Repository (application: Application){

    private val contactDao: DAO?
    private var contacts: LiveData<List<Contact>>? = null

    init {
        val db =AppDatabase.getInstance(application.applicationContext)
        contactDao = db?.contactDao()
        contacts = contactDao?.getContact()
    }

    fun getContact():LiveData<List<Contact>>? {
        return contacts
    }

    fun insert(con: Contact) = runBlocking {
        this.launch(Dispatchers.IO) {
            contactDao?.insert(con)
        }
    }

    fun delete(con: Contact){
        runBlocking {
            this.launch(Dispatchers.IO) {
                contactDao?.deleteContact(con)
            }
        }
    }

    fun update(con: Contact) = runBlocking {
        this.launch(Dispatchers.IO) {
            contactDao?.updateContact(con)
        }
    }

}
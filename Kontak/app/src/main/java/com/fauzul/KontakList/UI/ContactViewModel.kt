package com.fauzul.KontakList.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.fauzul.KontakList.storage.Contact
import com.fauzul.KontakList.storage.Repository

class ContactViewModel(application: Application): AndroidViewModel(application) {
    private var contactRepo = Repository(application)
    private var contacts: LiveData<List<Contact>>? = contactRepo.getContact()

    fun insert(contact: Contact){
        contactRepo.insert(contact)
    }

    fun getContact(): LiveData<List<Contact>>?{
        return contacts
    }

    fun deleteContact(contact: Contact){
        contactRepo.delete(contact)
    }

    fun updateContact(contact: Contact){
        contactRepo.update(contact)
    }
}
package com.fauzul.KontakList.UI

import android.view.View
import kotlinx.android.synthetic.main.contact_items.view.*
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fauzul.KontakList.R
import com.fauzul.KontakList.storage.Contact

class Adapter(private val context: Context?, private val listener: (Contact, Int)-> Unit) :
    RecyclerView.Adapter<ContactViewHolder>() {
    private var contacts = listOf<Contact>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.contact_items,
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        if (context != null){
            holder.bindItem(context,contacts[position],listener)
        }
    }

    override fun getItemCount(): Int = contacts.size

    fun setContact(contacts: List<Contact>){
        this.contacts = contacts
        notifyDataSetChanged()
    }

}
class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bindItem(context: Context, con: Contact, listener: (Contact, Int) -> Unit){
        itemView.nama.text = con.nama
        itemView.email.text = con.email
        itemView.notelp.text = con.notelp

        itemView.setOnClickListener {
            listener(con, layoutPosition)
        }
    }
}
package com.fauzul.KontakList.UI

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzul.KontakList.R
import com.fauzul.KontakList.storage.Contact
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private lateinit var Adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactRV.layoutManager = LinearLayoutManager(this)
        Adapter = Adapter(this){ con, i -> showAlertMenu(con)
        }
        contactRV.adapter = Adapter

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.getContact()?.observe(this, Observer {
            Adapter.setContact(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionItemSelected(item:MenuItem): Boolean{
        when (item.itemId){
            R.id.addMenu -> showAlertDialogAdd()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialogAdd(){
        val context: Context = applicationContext
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        val alert = AlertDialog.Builder(this)
        val editNama = EditText(applicationContext)
        editNama.hint = "Name"
        layout.addView(editNama)

        val editemail = EditText(applicationContext)
        editemail.hint = "email"
        layout.addView(editemail)

        val editNomor = EditText(applicationContext)
        editNomor.hint = "Telephone Number"
        layout.addView(editNomor)

        alert.setTitle("New Contact")
        alert.setView(layout)

        alert.setPositiveButton("Save") { dialog, _ ->
            contactViewModel.insert(
                Contact(nama = editNama.text.toString(),
                email = editemail.text.toString(),
                notelp = editNomor.text.toString())
            )
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") {
            dialog, _ -> dialog.dismiss()
        }
        alert.show()

    }

    private fun showAlertMenu(contact: Contact){
        val items = arrayOf("Edit", "Delete")

        val builder=
            AlertDialog.Builder(this)
        builder.setItems(items){ dialog, which ->
            when (which){
                0 -> {
                    showAlertDialogEdit(contact)
                }
                1-> {
                    contactViewModel.deleteContact(contact)
                }
            }
        }
        builder.show()
    }

    private fun showAlertDialogEdit(contact: Contact){
        val alert = AlertDialog.Builder(this)
        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL
        val editNama = EditText(applicationContext)
        val editemail = EditText(applicationContext)
        val editNomor = EditText(applicationContext)
        editNama.setText(contact.nama)
        editemail.setText(contact.email)
        editNomor.setText(contact.notelp)

        layout.addView(editNama)
        layout.addView(editemail)
        layout.addView(editNomor)
        alert.setTitle("New Contact")
        alert.setView(layout)

        alert.setPositiveButton("Save") { dialog, _ ->
            contactViewModel.insert(
                Contact(nama = editNama.text.toString(),
                    email = editemail.text.toString(),
                    notelp = editNomor.text.toString())
            )
            dialog.dismiss()
        }
        alert.setNegativeButton("Cancel") {
                dialog, _ -> dialog.dismiss()
        }
        alert.show()
    }
}
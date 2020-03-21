package com.example.quizsqlite01.helper

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.quizsqlite01.R

//Fungsi adapter bertindak sebagai penghubung antara kumpulan data dan tampilan adaptor, objek dari kelas yang memperluas kelas AdapterView abstrak. Data set mampu menyajikan data apa saja secara terstruktur. Array, List objects, dan Cursor object adalah kumpulan data yang umum digunakan.
class MyAdapter(private val context: Activity, private val id: Array<String>,
                private val name: Array<String>, private val email: Array<String>, private val nohp: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val idText = rowView.findViewById(R.id.textViewId) as TextView
        val nameText = rowView.findViewById(R.id.textViewName) as TextView
        val emailText = rowView.findViewById(R.id.textViewEmail) as TextView
        val nohpText = rowView.findViewById(R.id.textViewNohp) as TextView

        idText.text = "Id: ${id[position]}"
        nameText.text = "Name: ${name[position]}"
        emailText.text = "Email: ${email[position]}"
        nohpText.text = "No Hp: ${nohp[position]}"
        return rowView
    }
}
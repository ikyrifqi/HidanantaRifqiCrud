package com.example.quizsqlite01

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.quizsqlite01.`object`.EmpModelClass
import com.example.quizsqlite01.helper.MyAdapter
import com.example.quizsqlite01.model.DatabaseHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
    }

    //Menyimpan data yang sudah direcord ke dalam database
    fun saveRecord(view: View){
        val id = u_id.text.toString()
        val name = u_name.text.toString()
        val email = u_email.text.toString()
        val nohp = u_nohp.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if(id.trim()!="" && name.trim()!="" && email.trim()!="" && nohp.trim()!=""){
            val status = databaseHandler.addEmployee(EmpModelClass(Integer.parseInt(id),name, email, nohp))
            if(status > -1){
                Toast.makeText(applicationContext,"Data Employee saved",Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_name.text.clear()
                u_email.text.clear()
                u_nohp.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"Please fill the ID and Email",Toast.LENGTH_LONG).show()
        }

    }
    // Membaca suatu data dari database dan menampilkannya dari listview
    fun viewRecord(view: View){
        // Proses pembuatan databasehanler
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)

        // Memanggil fungsi viewemployee dari databsehandler untuk mengambil data
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}
        val empArrayNohp = Array<String>(emp.size){"null"}
        var index = 0

        // Setiap data yang didapatkan dari database akan dimasukkan ke dalam arraynya
        for(e in emp){
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            empArrayNohp[index] = e.userNohp
            index++
        }

        // Custom adapter dibuat untuk menampilkannya di dalam UI
        val myListAdapter = MyAdapter(this,empArrayId,empArrayName,empArrayEmail, empArrayNohp)
        listView.adapter = myListAdapter
    }

    // Fungsi untuk memperbarui data sesuai id user
    fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId) as EditText
        val edtName = dialogView.findViewById(R.id.updateName) as EditText
        val edtEmail = dialogView.findViewById(R.id.updateEmail) as EditText
        val edtNohp = dialogView.findViewById(R.id.updateNohp) as EditText

        dialogBuilder.setTitle("Update Data")
        dialogBuilder.setMessage("Pleae fill the field")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateName = edtName.text.toString()
            val updateEmail = edtEmail.text.toString()
            val updateNohp = edtNohp.text.toString()

            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            if(updateId.trim()!="" && updateName.trim()!="" && updateEmail.trim()!="" && updateNohp.trim()!=""){

                val status = databaseHandler.updateEmployee(EmpModelClass(Integer.parseInt(updateId),updateName, updateEmail, updateNohp))
                if(status > -1){
                    Toast.makeText(applicationContext,"Data Updated",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"Please fill the ID and Email",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            //Tidak terjadi apa apa
        })
        val b = dialogBuilder.create()
        b.show()
    }

    // Menghapus data sesuai ID yang ada di listview
    fun deleteRecord(view: View){
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Data")
        dialogBuilder.setMessage("Insert The Data's ID that you want to delete")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()

            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            if(deleteId.trim()!=""){

                val status = databaseHandler.deleteEmployee(EmpModelClass(Integer.parseInt(deleteId),"","", ""))
                if(status > -1){
                    Toast.makeText(applicationContext,"Data Telah Dihapus",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"Silahkan isi idnya",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Batal", DialogInterface.OnClickListener { _, _ ->

        })
        val b = dialogBuilder.create()
        b.show()
    }
}
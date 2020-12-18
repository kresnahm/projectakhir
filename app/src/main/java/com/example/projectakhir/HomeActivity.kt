package com.example.projectakhir

import Car
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.list_fragment.cList


class HomeActivity : AppCompatActivity() {
    private lateinit var mAdapter: FirestoreRecyclerAdapter<Car, CarAdapter.CarViewHolder>
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mCarCollection = mFirestore.collection("Car")
    private val mQuery = mCarCollection.orderBy("plat_nomor", Query.Direction.ASCENDING)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        initView()
        setupAdapter()
    }

    override fun onStart() {
        super.onStart()
        readData(mFirestore)
        mAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mAdapter.stopListening()
    }

    private fun initView() {
        Log.i("[HOME ACTIVITY]", "INIT VIEW")
        cList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }
        fab_firedb.setOnClickListener {
            Log.i("[HOME ACTIVITY]", "ONCLICKLISTENER")
            val intent = Intent(this, AddCarActivity2::class.java)
            startActivity(intent)
        }
        logOut.setOnClickListener {
            logoutDialog()
        }
    }

    private fun setupAdapter() {
        //set adapter yang akan menampilkan data pada recyclerview
        val options = FirestoreRecyclerOptions.Builder<Car>()
            .setQuery(mQuery, Car::class.java)
            .build()

        mAdapter = CarAdapter(this, mCarCollection, options)
        mAdapter.notifyDataSetChanged()
        cList.adapter = mAdapter
    }

    private fun readData(db: FirebaseFirestore) {
        db.collection("Car").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("[DATA]", "Datanya : ${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("[ERROR]", "Error getting documents : $exception")
            }
    }

    private fun logoutDialog() {
        val builder =
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setPositiveButton("Yes") { dialog, which ->
                    logout()
                }
                .setNegativeButton("Cancel", null)
        builder.create().show()
    }

    private fun logout() {
        var out = Intent(this, LoginActivity::class.java)
        startActivity(out)
        finish()
    }
}


//    private fun moveIntent(){
//        startActivity(Intent(this, LoginActivity::class.java))
//        finish()
//    }
//
//    private fun showMessage(message: String) {
//        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.home_activity)
//
//        val db = FirebaseFirestore.getInstance()
//        writeData(db) //menulis data
//        readData(db) //membaca data
//    }

//    private fun writeData(db: FirebaseFirestore) {
//        val car = hashMapOf(
//            "cc" to 1000,
//            "kursi" to 6,
//            "merk" to "Daihatsu",
//            "nama" to "Xenia",
//            "plat_nomor" to "C342SS",
//            "transmisi" to "Auto"
//        )
//        db.collection("car").add(car)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error adding document : ${exception.message}")
//            }
//    }
//
//    private fun readData(db: FirebaseFirestore) {
//        db.collection("car").get()
//            .addOnSuccessListener { result ->
//                for (document in result){
//                    Log.d(TAG, "Datanya : ${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents : $exception")
//            }
//    }
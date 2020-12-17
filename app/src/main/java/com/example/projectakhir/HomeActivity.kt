package com.example.projectakhir

import Car
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.list_fragment.*

class HomeActivity: AppCompatActivity() {
//    private val TAG: String = javaClass.simpleName
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.home_activity)
//
//        val db = FirebaseFirestore.getInstance()
//        writeData(db) //menulis data
////        readData(db) //membaca data
//    }
//
//    private fun writeData(db: FirebaseFirestore) {
//        val user = hashMapOf(
//            "cc" to 1000,
//            "kursi" to 6,
//            "merk" to "Daihatsu",
//            "nama" to "Xenia",
//            "plat_nomor" to "C342SS",
//            "transmisi" to "Auto"
//        )
//        db.collection("Car").add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error adding document : ${exception.message}")
//            }
//    }

//    private fun readData(db: FirebaseFirestore) {
//        db.collection("users").get()
//            .addOnSuccessListener { result ->
//                for (document in result){
//                    Log.d(TAG, "Datanya : ${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents : $exception")
//            }
//    }
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

    private fun initView() {



        cList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }
//        fab_firedb.setOnClickListener {
//            //berpindah ke activity AddEditActivity untuk tambah data
//            startActivity(Intent(this, AddEditActivity::class.java).apply {
//                putExtra(AddEditActivity.REQ_EDIT, false)
//            })
//        }
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





    override fun onStart() {
        super.onStart()
        mAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mAdapter.stopListening()
    }

//    private fun moveIntent(){
//        startActivity(Intent(this, LoginActivity::class.java))
//        finish()
//    }
//
//    private fun showMessage(message: String) {
//        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
//    }
}
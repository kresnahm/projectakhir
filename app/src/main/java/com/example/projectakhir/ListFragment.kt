//package com.example.projectakhir
//
//import Car
//import android.content.Intent
//import android.os.Bundle
//import android.view.*
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.firebase.ui.firestore.FirestoreRecyclerAdapter
//import com.firebase.ui.firestore.FirestoreRecyclerOptions
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.Query
//import kotlinx.android.synthetic.main.list_fragment.*
//
//
//class ListFragment :Fragment() {
//    private lateinit var mAdapter: FirestoreRecyclerAdapter<Car, CarAdapter.CarViewHolder>
//    private val mFirestore = FirebaseFirestore.getInstance()
//    private val mCarCollection = mFirestore.collection("Car")
//    private val mQuery = mCarCollection.orderBy("nomor", Query.Direction.ASCENDING)
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val displayView = inflater.inflate(R.layout.list_fragment, container, false)
//
//        return displayView
//    }
//
//    private fun initView() {
//
//
////        val textUsername = findViewById<TextView>(R.id.textUsername)
////        textUsername.text = prefHelper.getString( Constant.PREF_USERNAME )
//
//        cList.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(this@ListFragment)
//        }
////        fab_firedb.setOnClickListener {
////            //berpindah ke activity AddEditActivity untuk tambah data
////            startActivity(Intent(this, AddEditActivity::class.java).apply {
////                putExtra(AddEditActivity.REQ_EDIT, false)
////            })
////        }
//    }
//
//    private fun setupAdapter() {
//        //set adapter yang akan menampilkan data pada recyclerview
//        val options = FirestoreRecyclerOptions.Builder<Car>()
//            .setQuery(mQuery, Car::class.java)
//            .build()
//        mAdapter = CarAdapter(this, mCarCollection, options)
//        mAdapter.notifyDataSetChanged()
//        cList.adapter = mAdapter
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.dropdown, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.btn_logout -> {
//                prefHelper.clear()
//                showMessage("Keluar")
//                moveIntent()
//
//                true
//            }
//            else -> false
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        mAdapter.startListening()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mAdapter.stopListening()
//    }
//
//    private fun moveIntent(){
//        startActivity(Intent(this, LoginActivity::class.java))
//        finish()
//    }
//
//    private fun showMessage(message: String) {
//        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
//    }
//}
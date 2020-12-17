package com.example.projectakhir

import Car
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import kotlinx.android.synthetic.main.list_row.view.*

class CarAdapter(
    private val context: Context,
    private val collection: CollectionReference,
    options: FirestoreRecyclerOptions<Car>
) : FirestoreRecyclerAdapter<Car, CarAdapter.CarViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false))
    }

    override fun onBindViewHolder(viewHolder: CarViewHolder, position: Int, cars: Car) {
        viewHolder.bindItem(cars)
//        viewHolder.itemView.setOnClickListener {
//            showDialogMenu(cars)
//        }
    }

    class CarViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(cars: Car) {
            view.apply {
                val cc = "CC  : ${cars.kapasitasMesin}"
                val kursi = "Jumlah Kursi   : ${cars.jumlahKursi}"
                val merkM = "Merk : ${cars.merkMobil}"
                val nama = "Nama : ${cars.namaMobil}"
                val plat_nomor = "${cars.platNomor}"
                val transmisiM = "Transmisi : ${cars.jenisTransmisi}"

                plat_no.text = plat_nomor
                namaM.text = nama
                merk.text = merkM
                cc_mobil.text = cc
                jml_kursi.text = kursi
                transmisi.text = transmisiM
            }
        }
    }

//    private fun showDialogMenu(cars: Car) {
//        // dialog popup menu edit dan hapus
//        val builder = AlertDialog.Builder(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
//        val option = arrayOf("Edit", "Hapus")
//        builder.setItems(option) { dialog, which ->
//            when (which) {
//                //0 -> untuk berpindah ke activity AddEditActivity untuk edit dengan membawa data
//                0 -> context.startActivity(Intent(context, AddEditActivity::class.java).apply {
//                    putExtra(AddEditActivity.REQ_EDIT, true)
//                    putExtra(AddEditActivity.EXTRA_DATA, cars)
//                })
//                1 -> showDialogDel(cars.id)
//            }
//        }
//        builder.create().show()
//    }

//    private fun showDialogDel(strId: String) {
//        //dialog pop delete
//        val builder = AlertDialog.Builder(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
//            .setTitle("Hapus Data")
//            .setMessage("Yakin nih mau hapus?")
//            .setPositiveButton(android.R.string.yes) { dialog, which ->
//                deleteById(strId)
//            }
//            .setNegativeButton(android.R.string.cancel, null)
//        builder.create().show()
//    }

//    private fun deleteById(id: String) {
//        //menghapus data berdasarkan id
//        collection.document(id)
//            .delete()
//            .addOnCompleteListener { Toast.makeText(context, "Succes Hapus data", Toast.LENGTH_SHORT).show() }
//            .addOnFailureListener { Toast.makeText(context, "Gagal Hapus data", Toast.LENGTH_SHORT).show() }
//    }

}
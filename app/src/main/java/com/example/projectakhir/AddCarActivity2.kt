package com.example.projectakhir

import Car
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_car_activity_2.*
import java.util.*

class AddCarActivity2 : AppCompatActivity() {
    lateinit var platNomor: EditText
    lateinit var namaMobil: EditText
    lateinit var merkMobil: EditText
    lateinit var kapasitasMesin: EditText
    lateinit var jumlahKursi: EditText
    lateinit var jenisTransmisi: RadioButton

    private val cars: Car? = null
    private val mFirestore = FirebaseFirestore.getInstance()
    private val carCollection = mFirestore.collection("Car")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_car_activity_2)

        btnSave.setOnClickListener {
            createDataMobil(null).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Sukses tambah data", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Error Added data ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createDataMobil(strId: String?): Task<Void> {
        val writeBatch = mFirestore.batch()
        val path = "Cars" + _setTimeStamp().toString()
        val id = strId ?: path

        var plat_nomor = findViewById<EditText>(R.id.etPlatNomor).text.toString()
        var nama = findViewById<EditText>(R.id.etNamaMobil).text.toString()
        var merk = findViewById<EditText>(R.id.etMerkMobil).text.toString()
        var cc = findViewById<EditText>(R.id.etKapasitasMesin).text.toString()
        var kursi = findViewById<EditText>(R.id.etJumlahKursi).text.toString()
        var transmisiRadioId = findViewById<RadioGroup>(R.id.rgTransmission).checkedRadioButtonId
        var transmisiValue = findViewById<RadioButton>(transmisiRadioId).text.toString()

        val cars  = Car(plat_nomor, nama, merk, cc.toInt(), kursi.toInt(), transmisiValue)
        writeBatch.set(carCollection.document(plat_nomor), cars)
        return writeBatch.commit()
    }

    private fun _setTimeStamp(): Long {
        val time = (-1 * System.currentTimeMillis())
        return time
    }
}
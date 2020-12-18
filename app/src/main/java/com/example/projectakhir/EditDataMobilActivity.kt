package com.example.projectakhir

import Car
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_car_activity_2.*
import kotlinx.android.synthetic.main.list_row.*

class EditDataMobilActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra data"
    }

    private var car: Car? = null
    private val mFirestore = FirebaseFirestore.getInstance()
    private val carCollection = mFirestore.collection("Car")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data_mobil)

        car = intent.getParcelableExtra(EXTRA_DATA)

        btnSave.setOnClickListener {
            saveData(car!!.plat_nomor).addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(this, "Sukses edit data", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Gagal edit data", Toast.LENGTH_SHORT).show()
                }
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Error Edit data ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        initView()
    }

//    private fun updateData(platNomor: String?) {
//        var plat_nomor = findViewById<EditText>(R.id.etPlatNomor).text.toString()
//        var nama = findViewById<EditText>(R.id.etNamaMobil).text.toString()
//        var merk = findViewById<EditText>(R.id.etMerkMobil).text.toString()
//        var cc = findViewById<EditText>(R.id.etKapasitasMesin).text.toString()
//        var kursi = findViewById<EditText>(R.id.etJumlahKursi).text.toString()
//        var transmisiRadioId = findViewById<RadioGroup>(R.id.rgTransmission).checkedRadioButtonId
//        var transmisiValue = findViewById<RadioButton>(transmisiRadioId).text.toString()
//
//        val updatedCar = Car(plat_nomor, nama, merk, cc.toInt(), kursi.toInt(), transmisiValue)
//
//        mFirestore.collection("Car").document(plat_nomor)
//            .update(
//                mapOf(
//                    "plat_nomor" to plat_nomor,
//                    "nama" to nama,
//                    "merk" to merk,
//                    "cc" to cc,
//                    "kursi" to kursi,
//                    "transmisi" to transmisiValue
//                    )
//                )
//            .addOnCompleteListener { Toast.makeText(this, "Sukses edit data", Toast.LENGTH_SHORT).show() }
//            .addOnFailureListener { e -> Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show() }
//        finish()
//    }

    private fun saveData(platNomor: String?): Task<Void> {
        val writeBatch = mFirestore.batch()

        var plat_nomor = findViewById<EditText>(R.id.etPlatNomor).text.toString()
        var nama = findViewById<EditText>(R.id.etNamaMobil).text.toString()
        var merk = findViewById<EditText>(R.id.etMerkMobil).text.toString()
        var cc = findViewById<EditText>(R.id.etKapasitasMesin).text.toString()
        var kursi = findViewById<EditText>(R.id.etJumlahKursi).text.toString()
        var transmisiRadioId = findViewById<RadioGroup>(R.id.rgTransmission).checkedRadioButtonId
        var transmisiValue = findViewById<RadioButton>(transmisiRadioId).text.toString()

        val updatedCar = Car(plat_nomor, nama, merk, cc.toInt(), kursi.toInt(), transmisiValue)

        writeBatch.set(carCollection.document(plat_nomor), updatedCar)

        return writeBatch.commit()
    }


    @SuppressLint("SetTextI18n")
    private fun initView() {
        etPlatNomor.text = Editable.Factory.getInstance().newEditable(car?.plat_nomor)
        etJumlahKursi.text = Editable.Factory.getInstance().newEditable(car?.kursi.toString())
        etKapasitasMesin.text = Editable.Factory.getInstance().newEditable(car?.cc.toString())
        etMerkMobil.text = Editable.Factory.getInstance().newEditable(car?.merk)
        etNamaMobil.text = Editable.Factory.getInstance().newEditable(car?.nama)
        when(car?.transmisi) {
            "Automatic" -> {
                findViewById<RadioGroup>(R.id.rgTransmission).check(R.id.rbAutomatic)
            }
            "Manual" -> {
                findViewById<RadioGroup>(R.id.rgTransmission).check(R.id.rbManual)
            }
        }
    }

}
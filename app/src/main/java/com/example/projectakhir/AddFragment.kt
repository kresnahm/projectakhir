package com.example.projectakhir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment

class AddFragment:Fragment() {
    lateinit var platNomor: EditText
    lateinit var namaMobil: EditText
    lateinit var merkMobil: EditText
    lateinit var kapasitasMesin: EditText
    lateinit var jumlahKursi: EditText
    lateinit var jenisTransmisi: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val displayView = inflater.inflate(R.layout.add_fragment, container, false)

        return displayView
    }

    override fun onStart() {
        super.onStart()
    }
}
package com.uti.ta_pm_22a_teamlontong

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        val spinnerTanaman: Spinner = findViewById(R.id.spinnerTanaman)
        val txNama: TextView = findViewById(R.id.txNama)
        val txKategori: TextView = findViewById(R.id.txKategori)
        val txWaktu: TextView = findViewById(R.id.txWaktu)
        val txModal: TextView = findViewById(R.id.txModal)
        val txJual: TextView = findViewById(R.id.txJual)
        val txHasil: EditText = findViewById(R.id.txHasil)
        val txUntung: TextView = findViewById(R.id.txUntung)
        val buttonHitung: Button = findViewById(R.id.button)


}
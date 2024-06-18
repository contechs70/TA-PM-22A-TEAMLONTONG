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

        // Masukkan data contoh saat pertama kali aplikasi dijalankan
        val initialData = listOf(
            Tanaman("Kentang", "Umbi-Umbian", "120 Hari", 18000, 30000),
            Tanaman("Ubi Jalar", "Umbi-Umbian", "4 Bulan", 8000, 27000),
            Tanaman("Singkong", "Umbi-Umbian", "8 Bulan", 3000, 15000),
            Tanaman("Ubi Ungu", "Umbi-Umbian", "3 Bulan", 8000, 40000),
            Tanaman("Padi", "Biji-Bijian", "180 Hari", 12000, 14000),
            Tanaman("Kacang Tanah", "Biji-Bijian", "120 hari", 22000, 30000),
            Tanaman("Jagung Manis", "Biji-Bijian", "80 Hari", 15000, 45000),
            Tanaman("Kedelai", "Biji-Bijian", "120 Hari", 12000, 25000)
        )
        initialData.forEach { tanaman ->
            dbHelper.insertData(tanaman)
        }

        val tanamanNames = initialData.map { it.nama }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tanamanNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTanaman.adapter = adapter

        spinnerTanaman.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedNama = parent.getItemAtPosition(position) as String
                val tanaman = dbHelper.getTanamanByName(selectedNama)

                if (tanaman != null) {
                    txNama.text = tanaman.nama
                    txKategori.text = tanaman.kategori
                    txWaktu.text = tanaman.waktuPanen
                    txModal.text = tanaman.modalBibitPerKg.formatRupiah()
                    txJual.text = tanaman.hargaJualPerKg.formatRupiah()
                } else {
                    txNama.text = ""
                    txKategori.text = ""
                    txWaktu.text = ""
                    txModal.text = ""
                    txJual.text = ""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                txNama.text = ""
                txKategori.text = ""
                txWaktu.text = ""
                txModal.text = ""
                txJual.text = ""
            }
        }

        buttonHitung.setOnClickListener {
            val hasilPanenStr = txHasil.text.toString()
            val hargaJualStr = txJual.text.toString()

            if (hasilPanenStr.isNotEmpty() && hargaJualStr.isNotEmpty()) {
                try {
                    val hasilPanen = hasilPanenStr.toInt()
                    val modalBibitPerKg = txModal.text.toString().replace("Rp", "").replace(".", "").toInt()
                    val hargaJualPerKg = hargaJualStr.replace("Rp", "").replace(".", "").toInt()

                    val totalModal = hasilPanen * modalBibitPerKg
                    val totalPenjualan = hasilPanen * hargaJualPerKg
                    val untung = totalPenjualan - totalModal

                    txUntung.text = untung.formatRupiah()
                } catch (e: NumberFormatException) {
                    txUntung.text = "Masukkan angka yang valid untuk jumlah panen dan harga jual"
                }
            }
}
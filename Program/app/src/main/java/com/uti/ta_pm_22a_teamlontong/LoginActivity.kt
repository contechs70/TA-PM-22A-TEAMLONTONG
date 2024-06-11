package com.uti.manajemen_panen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uti.manajemen_panen.Class.dbLogin
import com.uti.manajemen_panen.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //    deklarasi binding untuk databaseHelper
    private lateinit var databaseHelper: dbLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        buat variabel binding untuk LoginActivity
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
}
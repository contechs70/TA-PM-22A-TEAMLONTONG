package com.uti.manajemen_panen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uti.manajemen_panen.Class.dbLogin
import com.uti.ta_pm_22a_teamlontong.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var databaseHelper: dbLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding SignupActivity
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Binding database
        databaseHelper = dbLogin(this)

}
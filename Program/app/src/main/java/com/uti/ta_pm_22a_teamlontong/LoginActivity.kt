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

//        binding databaseHelper
        databaseHelper = dbLogin(this)

//        event btnLogin
        binding.btnLogin.setOnClickListener {
            val username = binding.Username.text.toString()
            val password = binding.Password.text.toString()
            loginDatabase(username, password)
        }

//        event txRegis
        binding.txRegis.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //    membaca username dan password dari SQLite
    private fun loginDatabase(username: String, password: String) {
//        periksa apakah username dan password sudah diisi
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username atau Password tidak boleh kosong !", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val userExists = databaseHelper.readUser(username, password)
        if (userExists) {
//            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Username atau Password tidak sesuai !", Toast.LENGTH_SHORT).show()
        }
    }
}
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

//        event btnRegis
        binding.btnRegis.setOnClickListener {
            val user = binding.Username.text.toString()
            val pass = binding.Password.text.toString()
            signupDatabase(user, pass)
        }

//        event txLogin
        binding.txLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
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

}
package com.example.local_site

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.local_site.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Button = findViewById<Button>(R.id.button2)
        Button.setOnClickListener{
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        val Button1 = findViewById<Button>(R.id.button1)
        Button1.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
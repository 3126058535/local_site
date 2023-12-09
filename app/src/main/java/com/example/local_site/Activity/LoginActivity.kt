package com.example.local_site.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.local_site.R

class LoginActivity : AppCompatActivity() {
    private lateinit var eButton: Button
    private lateinit var eAccount: EditText
    private lateinit var ePassword: EditText
    private lateinit var ezhuce: TextView
    private val correctUsername = "root"
    private val correctPassword = "123456"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        eButton = findViewById(R.id.bt_login)
        eAccount = findViewById(R.id.et_account)
        ePassword = findViewById(R.id.et_password)
        ezhuce = findViewById(R.id.zuche)
        ezhuce.setOnClickListener{
            val intent = Intent(this, registerActivity::class.java)
            startActivity(intent)
        }
        eButton.setOnClickListener {
            val enteredUsername = eAccount.text.toString()
            val enteredPassword = ePassword.text.toString()

            if (isValidCredentials(enteredUsername, enteredPassword)) {
                showToast("登录成功")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                showToast("用户名或密码错误")
            }
        }
    }
    private fun isValidCredentials(username: String, password: String): Boolean {
        return TextUtils.equals(username, correctUsername) && TextUtils.equals(password, correctPassword)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
package com.example.local_site.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.local_site.R

class registerActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        usernameEditText = findViewById(R.id.et_register_username)
        passwordEditText = findViewById(R.id.et_register_password)
        confirmPasswordEditText = findViewById(R.id.et_confirm_password)
        registerButton = findViewById(R.id.btn_register)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (isValidRegistration(username, password, confirmPassword)) {
                showToast("注册成功")
                navigateToLogin()
            }
        }
    }

    private fun isValidRegistration(username: String, password: String, confirmPassword: String): Boolean {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            showToast("所有字段都是必填项")
            return false
        }

        if (password != confirmPassword) {
            showToast("密码和确认密码不匹配")
            return false
        }

        // 可以添加其他注册条件的检查

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()  // 关闭当前注册界面，防止用户通过返回按钮回到注册界面
    }
}
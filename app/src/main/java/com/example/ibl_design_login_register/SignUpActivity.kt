package com.example.ibl_design_login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private fun isValidEmail(target: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        tvInHide.setOnClickListener {
            if (tvInHide.text.toString() == "Hide") {
                edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                tvInHide.text = "Show"

            } else {
                edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                tvInHide.text = "Hide"
            }
        }

        edtName?.addTextChangedListener(loginTextWatcher)
        edtEmail?.addTextChangedListener(loginTextWatcher)
        edtPassword?.addTextChangedListener(loginTextWatcher)

        btnSignUp!!.setOnClickListener {
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val name = edtName?.text.toString().trim().length < 0
            val usernameInput = edtEmail?.text.toString().trim()
            val passwordInput = edtPassword?.text.toString().trim().length < 6
            btnSignUp?.isEnabled = !name && isValidEmail(usernameInput) && !passwordInput
        }

        override fun afterTextChanged(s: Editable) {}
    }

}
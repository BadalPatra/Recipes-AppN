package com.practice.recipesapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    var signUp: TextView? = null
    var login: TextView? = null
    var signupLayout: LinearLayout? = null
    var loginLayout: LinearLayout? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val email: EditText = findViewById(R.id.eMailsignup)
        val password: EditText = findViewById(R.id.passwordsignup)
        val signup_btn: Button = findViewById(R.id.btn_sign)
        val login_btn: Button = findViewById(R.id.btn_login)

        signup_btn.setOnClickListener {
            val mail = email.text.toString().trim()
            val pass = password.text.toString().trim()

            if (mail.isNotEmpty() && pass.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Registration Successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@login, HomeActivity::class.java))
                        } else {
                            Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please Enter Valid EmailId & Password", Toast.LENGTH_SHORT).show()
            }
        }

        login_btn.setOnClickListener {
            val mail = email.text.toString().trim()
            val pass = password.text.toString().trim()

            if (mail.isNotEmpty() && pass.isNotEmpty()) {
                auth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login Sucessfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@login, HomeActivity::class.java))
                        } else {
                            Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please Enter Valid EmailId & Password", Toast.LENGTH_SHORT).show()
            }
        }

        signUp = findViewById<View>(R.id.signUp) as TextView
        login = findViewById<View>(R.id.login) as TextView
        signupLayout = findViewById<View>(R.id.signupLayout) as LinearLayout
        loginLayout = findViewById<View>(R.id.loginLayout) as LinearLayout

        signUp!!.setOnClickListener {
            signUp!!.background = resources.getDrawable(R.drawable.switch_trcks, null)
            signUp!!.setTextColor(resources.getColor(R.color.text_color, null))
            login!!.background = null
            signupLayout!!.visibility = View.VISIBLE
            loginLayout!!.visibility = View.GONE
            login!!.setTextColor(resources.getColor(R.color.pink, null))
        }

        login!!.setOnClickListener {
            signUp!!.background = null
            signUp!!.setTextColor(resources.getColor(R.color.pink, null))
            login!!.background = resources.getDrawable(R.drawable.switch_trcks, null)
            signupLayout!!.visibility = View.GONE
            loginLayout!!.visibility = View.VISIBLE
            login!!.setTextColor(resources.getColor(R.color.text_color, null))
        }


    }
}
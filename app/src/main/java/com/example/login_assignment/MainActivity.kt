package com.example.login_assignment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_assignment.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.createAcc.setOnClickListener(){
            val intent = Intent(applicationContext,SignUp::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun login(view: View){
        val email = binding.etUserName.text.toString()
        val pswd = binding.etPassword.text.toString()

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter Email", Toast.LENGTH_LONG).show()
            return
        }
        if(TextUtils.isEmpty(pswd)){
            Toast.makeText(this,"Enter Password",Toast.LENGTH_LONG).show()
            return
        }

        auth.signInWithEmailAndPassword(email, pswd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(
                        baseContext,
                        "Successful login",
                        Toast.LENGTH_SHORT,
                    ).show()

                    val intent = Intent(applicationContext,UserData::class.java)
                    intent.putExtra("User Email", email)
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}
package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth= FirebaseAuth.getInstance()
btnSign.setOnClickListener {
    signUp()
}
        login.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
      finish()
        }
    }
    private fun signUp(){
        var email=emailSignup.text.toString()
        var password=passwordSignup.text.toString()
        if(email.isBlank()||password.isBlank()){
            Toast.makeText(this,"Please enter required fields!!", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
                    Toast.makeText(this,"SignUp successful!",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this,"Error creating user",Toast.LENGTH_SHORT).show()
                }
            }
    }
}
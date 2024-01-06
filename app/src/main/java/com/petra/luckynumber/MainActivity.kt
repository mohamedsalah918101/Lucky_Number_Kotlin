package com.petra.luckynumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username: EditText = findViewById(R.id.username)
        val inputnumber: EditText = findViewById(R.id.inputnumber)
        val login: Button = findViewById(R.id.login)

        login.setOnClickListener()
        {
            if(inputnumber.text.toString().toInt() < 7 && inputnumber.text.toString().toInt() != 0 && username.text.toString().isNotEmpty() ){
                val intent = Intent(this@MainActivity, ImageView::class.java)
                intent.putExtra("name", username.text.toString())
                intent.putExtra("inputnumber", inputnumber.text.toString().toInt())
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this@MainActivity, "Enter the Username & number less than 7",Toast.LENGTH_SHORT).show()
            }

        }
    }
}
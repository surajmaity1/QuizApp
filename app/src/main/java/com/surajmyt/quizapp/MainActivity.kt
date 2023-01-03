package com.surajmyt.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var btnPlay: Button
    lateinit var editName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlay = findViewById(R.id.btn_ply)
        editName = findViewById(R.id.input_name)
        btnPlay.setOnClickListener {
            if (editName.text.isEmpty()){
                Toast.makeText(this, "Enter Your Name First", Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, QuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, editName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}
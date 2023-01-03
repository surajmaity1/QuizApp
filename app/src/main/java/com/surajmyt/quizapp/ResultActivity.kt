package com.surajmyt.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val name: TextView = findViewById(R.id.user_name)
        val score: TextView = findViewById(R.id.score_text)
        val finisButton: Button = findViewById(R.id.finish_btn)

        name.text = intent.getStringExtra(Constants.USER_NAME)
        val totalQues = intent.getIntExtra(Constants.TOTAL_QUES, 0)
        val correctAns = intent.getIntExtra(Constants.CORRECT_ANS, 0)

        score.text = "Your Score: $correctAns out of $totalQues"
        finisButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }

    }
}
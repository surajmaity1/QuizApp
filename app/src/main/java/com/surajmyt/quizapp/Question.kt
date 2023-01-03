package com.surajmyt.quizapp

data class Question(
    val id: Int,
    val question: String,
    val img: Int,
    val op1: String,
    val op2: String,
    val op3: String,
    val op4: String,
    val correctAns: Int
)

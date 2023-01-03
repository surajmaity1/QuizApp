package com.surajmyt.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var progressBar: ProgressBar?=null
    private var progressBarTxt: TextView? = null
    private var questionTxt:TextView? = null
    private var image: ImageView? = null
    private var option1:TextView? = null
    private var option2:TextView? = null
    private var option3:TextView? = null
    private var option4:TextView? = null
    private var nextButton: Button? = null

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0
    private var mUserName: String? = null
    private var mCorrectAns: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        progressBarTxt = findViewById(R.id.progress_bar_text)
        questionTxt = findViewById(R.id.question_txt)
        image = findViewById(R.id.image_view)
        option1 = findViewById(R.id.op1)
        option2 = findViewById(R.id.op2)
        option3 = findViewById(R.id.op3)
        option4 = findViewById(R.id.op4)
        nextButton = findViewById(R.id.next_button)

        option1?.setOnClickListener(this)
        option2?.setOnClickListener(this)
        option3?.setOnClickListener(this)
        option4?.setOnClickListener(this)

        nextButton?.setOnClickListener(this)

        mQuestionList = Constants.getQuestions()

        setAllQuestion()
    }
    private fun setAllQuestion(){
        defaultOptionView()

        val question : Question = mQuestionList!![mCurrentPosition - 1]

        progressBar?.progress = mCurrentPosition
        progressBarTxt?.text = "$mCurrentPosition/${progressBar?.max}"

        image?.setImageResource(question.img)

        questionTxt?.text = question.question
        option1?.text = question.op1
        option2?.text = question.op2
        option3?.text = question.op3
        option4?.text = question.op4

        if (mCurrentPosition == mQuestionList!!.size){
            nextButton?.text = "FINISH"
        }
    }


    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        option1?.let {
            options.add(0, it)
        }
        option2?.let {
            options.add(1, it)
        }
        option3?.let {
            options.add(2, it)
        }
        option4?.let {
            options.add(3, it)
        }

        for (eachOption in options){
            eachOption.setTextColor(Color.parseColor("#313639"))
            eachOption.typeface = Typeface.DEFAULT
            eachOption.background = ContextCompat.getDrawable(
                this,
                R.drawable.op_button_bg
            )
        }

    }
    private fun selectedOptionView(textView: TextView, selectedOption: Int){
        defaultOptionView()
        mSelectedOption = selectedOption

        textView.setTextColor(Color.parseColor("#FF3700B3"))
        textView.setTypeface(textView.typeface, Typeface.BOLD_ITALIC)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.button_color_after_selecting
        )
    }
    override fun onClick(view: View?) {
        when (view?.id){
            R.id.op1 -> {
                option1?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.op2 -> {
                option2?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.op3 -> {
                option3?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.op4 -> {
                option4?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.next_button -> {
                if (mSelectedOption == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setAllQuestion()
                        }
                        //  WHEN ALL QUESTIONS ARE ATTEMPTED
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)

                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANS, mCorrectAns)
                            intent.putExtra(Constants.TOTAL_QUES, mQuestionList?.size)

                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else{
                    val quesPos = mQuestionList?.get(mCurrentPosition-1)
                    if (quesPos!!.correctAns != mSelectedOption){
                        answerView(mSelectedOption, R.drawable.option_button_wrong_border)
                    }
                    else{
                        mCorrectAns++
                    }
                    answerView(quesPos.correctAns, R.drawable.option_button_correct_border)

                    if (mCurrentPosition == mQuestionList!!.size){
                        nextButton?.text = getString(R.string.finish_ques)
                    }
                    else {
                        nextButton?.text = getString(R.string.go_to_next_ques)
                    }
                    mSelectedOption = 0
                }
            }
        }
    }

    private fun answerView(ans: Int, drawableView: Int){
        when (ans) {
            1 -> {
                option1?.background = ContextCompat
                    .getDrawable(
                        this@QuestionsActivity,
                        drawableView
                    )
            }
            2 -> {
                option2?.background = ContextCompat
                    .getDrawable(
                        this@QuestionsActivity,
                        drawableView
                    )
            }
            3 -> {
                option3?.background = ContextCompat
                    .getDrawable(
                        this@QuestionsActivity,
                        drawableView
                    )
            }
            4 -> {
                option4?.background = ContextCompat
                    .getDrawable(
                        this@QuestionsActivity,
                        drawableView
                    )
            }
        }
    }
}
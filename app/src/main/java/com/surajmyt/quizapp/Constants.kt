package com.surajmyt.quizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUES: String = "total_ques"
    const val CORRECT_ANS: String = "correct_ans"

    fun getQuestions(): ArrayList<Question>{
        val quesList = ArrayList<Question>()

        val q1 = Question(
            1,"This Flag belongs to ?",
            R.drawable.india, "India", "Australia", "Brazil",
            "Japan", 1
        )
        quesList.add(q1)

        val q2 = Question(
            2,"Identify this Image",
            R.drawable.bird, "Cat", "Elephant", "Dog",
            "Bird", 4
        )
        quesList.add(q2)
        val q3 = Question(
            3,"Identify this Image",
            R.drawable.messi, "Messi", "Ronaldo ", "Salah",
            "Mbappe", 1
        )
        quesList.add(q3)
        val q4 = Question(
            4,"Identify this Image",
            R.drawable.elephant, "Dog", "Bird", "Elephant",
            "Cat", 3
        )
        quesList.add(q4)
        val q5 = Question(
            5,"Identify this Image",
            R.drawable.phonepay, "Gpay", "Phonepay", "Paytm",
            "Amazon Pay", 2
        )
        quesList.add(q5)

        return quesList
    }
}
package com.example.quizz_tcr

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQustionActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var btn_submit : Button
    lateinit var tv_option_one : TextView
    lateinit var tv_option_two : TextView
    lateinit var tv_option_three : TextView
    lateinit var tv_option_four : TextView
    lateinit var progressBar : ProgressBar
    lateinit var tv_progress : TextView
    lateinit var tv_question : TextView

    private var mCurrentPosition : Int = 1
    private var mQuestionList : ArrayList<Question>? = null
    private var mQuestionList1 : ArrayList<Question>? = null
    private var mQuestionList2 : ArrayList<Question>? = null
    private var mQuestionList3 : ArrayList<Question>? = null

    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswer : Int = 0
    private var mUserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_qustion)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        btn_submit = findViewById(R.id.btn_submit)
        tv_option_one = findViewById(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        tv_option_four = findViewById(R.id.tv_option_four)
        progressBar = findViewById(R.id.progressBar)
        tv_progress = findViewById(R.id.tv_progress)
        tv_question = findViewById(R.id.tv_question)

        mQuestionList = Constants.getQuestions()
        mQuestionList1 = Constants.getQuestions()
        mQuestionList2 = Constants.getQuestions()
        mQuestionList3 = Constants.getQuestions()


        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion() {

        val question = mQuestionList!!.get(mCurrentPosition - 1)
        val question1 = mQuestionList1!!.get(mCurrentPosition - 1)
        val question2 = mQuestionList2!!.get(mCurrentPosition - 1)
        val question3 = mQuestionList3!!.get(mCurrentPosition - 1)


        defaultOptionsView()
        if (mCurrentPosition == mQuestionList!!.size) {
            btn_submit.text = "Finish"
        } else {
            btn_submit.text = "Submit"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_question.text = question.question
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(tv_option_four, 4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this,Result::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctOption != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctOption, R.drawable.correct_option_border_bg)
                    if (mCurrentPosition == mQuestionList!!.size) {
                        btn_submit.text = "Finish"
                    } else {
                        btn_submit.text = "Go to next question"
                    }
                    mSelectedOptionPosition = 0
                }

            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    override fun onBackPressed() {

        val alertdialog = AlertDialog.Builder(this)
        alertdialog.setTitle("Exit Quiz ?")

            .setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(this,"You can't go back ",Toast.LENGTH_SHORT).show() })
            .setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int ->
            })

        alertdialog.show()
    }
}
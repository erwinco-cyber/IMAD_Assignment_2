package com.example.imad_assignment_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var currentScore = 0
        var currentQuestion = 0

        val questions = arrayOf("More Bars = faster internet",
            "Deleting an app deletes all the private data",
            "Incognito mode hides all your activities",
            "Airplane mode charges your phone faster"
        )
        val answers = arrayOf(false, false, false,true)
        val totalQuestions = questions.size
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btnShowQuestions = findViewById<Button>(R.id.btnShowQuestions)
        val btnShowResults = findViewById<Button>(R.id.btnResults)
        val btnShowNextQuestion = findViewById<Button>(R.id.btnNext)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val btnHack = findViewById<Button>(R.id.btnHack)
        val btnMyth = findViewById<Button>(R.id.btnMyth)

        val txtWelcome = findViewById<TextView>(R.id.txtWelcome)
        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtAllQuestions = findViewById<TextView>(R.id.txtAllQuestions)
        val txtReward = findViewById<TextView>(R.id.txtReward)
        val txtQuestion = findViewById<TextView>(R.id.txtQuestion)

        btnReset.setOnClickListener {
            btnHack.isEnabled = true
            btnMyth.isEnabled = true
            txtWelcome.visibility = View.VISIBLE
            btnShowQuestions.visibility = View.VISIBLE
            btnShowResults.visibility = View.INVISIBLE
            btnShowNextQuestion.visibility = View.INVISIBLE
            btnReset.visibility = View.INVISIBLE
            btnHack.visibility = View.INVISIBLE
            btnMyth.visibility = View.INVISIBLE
            txtScore.visibility = View.INVISIBLE
            txtAllQuestions.visibility = View.INVISIBLE
            txtReward.visibility = View.INVISIBLE
            txtQuestion.visibility = View.INVISIBLE
            currentScore = 0
            currentQuestion = 0
        }

        btnShowQuestions.setOnClickListener {
            txtWelcome.visibility = View.INVISIBLE
            btnShowQuestions.visibility = View.INVISIBLE
            btnReset.visibility = View.INVISIBLE
            if (currentQuestion < totalQuestions-1) {
                btnShowNextQuestion.visibility = View.VISIBLE
                btnShowResults.visibility = View.INVISIBLE
            } else {
                btnShowNextQuestion.visibility = View.INVISIBLE
                btnShowResults.visibility = View.VISIBLE
            }

            btnHack.visibility = View.VISIBLE
            btnMyth.visibility = View.VISIBLE
            txtScore.visibility = View.VISIBLE
            txtAllQuestions.visibility = View.INVISIBLE
            txtReward.visibility = View.INVISIBLE
            txtQuestion.visibility = View.VISIBLE
            txtScore.setText("Score: " + currentScore.toString())
            txtQuestion.setText(questions[currentQuestion])
        }

        btnShowResults.setOnClickListener {
            txtWelcome.visibility = View.INVISIBLE
            btnShowQuestions.visibility = View.INVISIBLE
            btnShowResults.visibility = View.INVISIBLE
            btnShowNextQuestion.visibility = View.INVISIBLE
            btnReset.visibility = View.VISIBLE
            btnHack.visibility = View.INVISIBLE
            btnMyth.visibility = View.INVISIBLE
            txtScore.visibility = View.INVISIBLE
            txtAllQuestions.visibility = View.VISIBLE
            txtReward.visibility = View.VISIBLE
            txtQuestion.visibility = View.INVISIBLE
            txtAllQuestions.setText("");
            var counter = 0;
            while (counter< totalQuestions) {
                if (answers[counter]) {
                    txtAllQuestions.append("HACK -" + questions[counter] + "\n")
                } else {
                    txtAllQuestions.append("MYTH -" + questions[counter] + "\n")
                }
                counter++
            }
            if (currentScore / totalQuestions >= 0.8) {
                txtReward.setText("You know your stuff!")
            } else {
                txtReward.setText("You need to learn more!")
            }
        }

        btnHack.setOnClickListener {
            btnHack.isEnabled = false
            btnMyth.isEnabled = false
            if (answers[currentQuestion]) {
                currentScore++
            }
        }

        btnMyth.setOnClickListener {
            btnHack.isEnabled = false
            btnMyth.isEnabled = false
            if (!answers[currentQuestion]) {
                currentScore++
            }
        }

        btnShowNextQuestion.setOnClickListener {
            btnHack.isEnabled = true
            btnMyth.isEnabled = true
            currentQuestion++
            btnShowQuestions.performClick()
        }
        btnReset.performClick()
    }
}
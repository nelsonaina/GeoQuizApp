package com.example.geoquiz

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.text.get

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
binding.questionTextView.setOnClickListener { view ->
    currentIndex = (currentIndex + 1) % questionBank.size
    updateQuestion()
}

        binding.trueButton.setOnClickListener { view ->
//            Toast.makeText(this, R.string.correct_toast,
//            Toast.LENGTH_LONG).show()
            checkAnswer(true)
            trueButton.isEnabled=false
            falseButton.isEnabled=false

        }


        binding.falseButton.setOnClickListener { view ->
//            Toast.makeText(this, R.string.incorrect_toast,
//            Toast.LENGTH_LONG).show()
                checkAnswer(false)
            trueButton.isEnabled=false
            falseButton.isEnabled=false
        }

        binding.previousButton.setOnClickListener { view ->
            currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
            updateQuestion()
            trueButton.isEnabled= true
            falseButton.isEnabled= true
        }

        binding.nextButton.setOnClickListener { view ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            trueButton.isEnabled= true
            falseButton.isEnabled= true
        }

        updateQuestion()


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }





    //functions
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast

        }
        else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()

    }

}





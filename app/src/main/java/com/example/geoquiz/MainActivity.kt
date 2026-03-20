package com.example.geoquiz

import android.app.Activity
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()

//    private val questionBank = listOf(
//        Question(R.string.question_australia, true),
//        Question(R.string.question_oceans, true),
//        Question(R.string.question_mideast, false),
//        Question(R.string.question_africa, false),
//        Question(R.string.question_americas, true),
//        Question(R.string.question_asia, true),
//    )

//    private var currentIndex = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d(TAG, "onCreate(Bundle?) is called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")


        binding.questionTextView.setOnClickListener { view ->
            quizViewModel.moveToNext()
            updateQuestion()
        }
        binding.trueButton.setOnClickListener { view ->
            checkAnswer(true)

        }

        binding.falseButton.setOnClickListener { view ->
            Toast.makeText(this, R.string.incorrect_toast,
            Toast.LENGTH_LONG).show()


        }
        binding.nextButton.setOnClickListener { view ->
            //currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion ()
        }
        binding.previousButton.setOnClickListener { view ->
            quizViewModel.moveToPrev()
            updateQuestion()
        }


        updateQuestion ()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() is called ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() is called ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() is called ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() is called ")
    }



    private fun updateQuestion ()
    {
        //val questionTextResId = questionBank [currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer (userAnswer : Boolean)
    {
        //val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId =
            if (userAnswer == correctAnswer)
            {
                R.string.correct_toast
            } else {
                R.string.incorrect_toast
            }
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG)
            .show()
    }
}

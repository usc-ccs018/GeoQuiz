package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityCheatBinding

private const val TAG = "CheatActivity"

class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding
    private val cheatViewModel : CheatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a CheatViewModel: $cheatViewModel")

        binding.showAnswerButton.setOnClickListener {
            binding.answerTextView.setText(cheatViewModel.trueOrFalse())
            setAnswerShownResult()
        }

        updateAnswerText()
    }

    private fun updateAnswerText() {
        if (cheatViewModel.answerShown) {
            binding.answerTextView.setText(cheatViewModel.trueOrFalse())
            setAnswerShownResult()
        }
    }

    private fun setAnswerShownResult() {
        setResult(Activity.RESULT_OK, cheatViewModel.data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}
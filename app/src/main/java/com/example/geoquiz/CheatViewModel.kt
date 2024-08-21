package com.example.geoquiz

import android.content.Intent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "CheatViewModel"
const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"
const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"

class CheatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var answerIsTrue: Boolean
        get() = savedStateHandle.get(EXTRA_ANSWER_IS_TRUE) ?: false
        set(value) = savedStateHandle.set(EXTRA_ANSWER_IS_TRUE, value)

    var answerShown: Boolean
        get() = savedStateHandle.get(EXTRA_ANSWER_SHOWN) ?: false
        set(value) = savedStateHandle.set(EXTRA_ANSWER_SHOWN, value)

    val data: Intent
        get() = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, true)
        }

    fun trueOrFalse(): String {
        answerShown = true
        if (answerIsTrue) {
            return "True"
        } else {
            return "False"
        }
    }
}
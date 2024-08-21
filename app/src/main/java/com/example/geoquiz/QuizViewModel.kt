package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val QUESTIONS_CHEATED_ON_KEY = "QUESTIONS_CHEATED_ON_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    private var cheatedQuestions: Set<Int>
        get() = savedStateHandle.get(QUESTIONS_CHEATED_ON_KEY) ?: mutableSetOf()
        set(value) = savedStateHandle.set(QUESTIONS_CHEATED_ON_KEY, value)

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentIndex = if (currentIndex == 0) {
            0
        } else {
            (currentIndex - 1) % questionBank.size
        }
    }

    fun didUserCheatOnThisQ() : Boolean {
        if (isCheater && (currentIndex !in cheatedQuestions)) {
            cheatedQuestions = cheatedQuestions.plus(currentIndex)
            return true
        } else if (currentIndex in cheatedQuestions) {
            return true
        } else {
            return false
        }
    }
}
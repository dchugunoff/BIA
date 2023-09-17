package com.bia_technologies.bia.presentation.ui.screens.authorisation_screen

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorisationViewModel @Inject constructor() :
    ViewModel() {


    private val _timer = MutableLiveData<String>()
    val timer: LiveData<String> = _timer

    private val _isCancel = MutableLiveData(false)
    val isCancel: LiveData<Boolean> = _isCancel

    fun updateTimerText(millisUntilFinishing: Long) {
        val seconds = millisUntilFinishing / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        val timerText = String.format("%02d:%02d", minutes, remainingSeconds)
        _timer.value = "Выслать повторный код ($timerText)"
    }

    fun startTimer() {
        _isCancel.value = false
        countDownTimer.start()
    }

    private var countDownTimer: CountDownTimer = object : CountDownTimer(15000, 1000) {
        override fun onTick(millisUntilFinishing: Long) {
            updateTimerText(millisUntilFinishing)
        }

        override fun onFinish() {
            updateTimerText(0)
            _isCancel.value = true
        }
    }
}

package com.cairosquad.evolvefit.design_system.component.clockTimer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ClockTimerState(
    val totalTimeSeconds: Int,
    private val coroutineScope: CoroutineScope,
    private val onFinish: (() -> Unit)? = null,
    isInitiallyPaused: Boolean = false,
) {

    private var isPaused = isInitiallyPaused
    private var timerJob: Job? = null

    private val _currentTimeSeconds = MutableStateFlow(totalTimeSeconds)
    val currentTime = _currentTimeSeconds.asSharedFlow()

    init {
        if (!isInitiallyPaused) { startTimer() }
    }

    private fun startTimer() {
        timerJob?.cancel()

        val startTime = totalTimeSeconds
        val endTime = 0
        val step = -1

        _currentTimeSeconds.update { startTime }
        timerJob = coroutineScope.launch {
            while (true) {
                delay(1000)
                if (isPaused) continue
                if (_currentTimeSeconds.value == endTime) {
                    onFinish?.invoke()
                    timerJob?.cancel()
                    break
                } else {
                    _currentTimeSeconds.update { it + step }
                }
            }
        }
    }


    fun reset(
    ) {
        startTimer()
    }

    fun pause() {
        isPaused = true
    }

    fun continueTimer() {
        isPaused = false
    }

    fun triggerPause() {
        isPaused = !isPaused
    }

    fun addSeconds(timeIncrementSeconds: Int) {
        if (timerJob?.isActive != true) return

        if (_currentTimeSeconds.value + timeIncrementSeconds > 0) {
            _currentTimeSeconds.update { it + timeIncrementSeconds }
            return
        }
        timerJob?.cancel()
        _currentTimeSeconds.update { 0 }
        onFinish?.invoke()
    }
}

@Composable
fun rememberClockTimerState(
    totalTime: Int,
    onFinish: (() -> Unit)? = null,
    isInitiallyPaused: Boolean = false,
): ClockTimerState {
    val coroutineScope = rememberCoroutineScope()
    return remember {
        ClockTimerState(
            totalTimeSeconds = totalTime,
            onFinish = onFinish,
            isInitiallyPaused = isInitiallyPaused,
            coroutineScope = coroutineScope
        )
    }
}
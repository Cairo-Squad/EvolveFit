package com.cairosquad.evolvefit.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<T, E>(
    initialState: T
) : ViewModel() {

    private val _screenState = MutableStateFlow(initialState)
    val screenState: StateFlow<T> = _screenState.asStateFlow()

    private val _effect = MutableSharedFlow<E>()
    val effect = _effect.asSharedFlow()

    fun updateState(transform: (T) -> T) {
        _screenState.update { transform(it) }
    }

    protected fun sendEffect(
        event: E,
        onStart: suspend () -> Unit = {},
        onEnd: suspend () -> Unit = {},
        dispatcher: CoroutineDispatcher = Dispatchers.Main
    ) {
        viewModelScope.launch(dispatcher) {
            onStart()
            _effect.emit(event)
            onEnd()
        }
    }

    protected fun <R> tryToCall(
        block: suspend () -> R,
        onSuccess: (R) -> Unit,
        onError: (Throwable) -> Unit,
        onStart: suspend () -> Unit = {},
        onEnd: suspend () -> Unit = {},
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Job {
        return viewModelScope.launch(dispatcher) {
            onStart()
            runCatching { block() }
                .onSuccess(onSuccess)
                .onFailure(onError)
            onEnd()
        }
    }
}
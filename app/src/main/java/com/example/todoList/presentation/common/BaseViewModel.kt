package com.example.todoList.presentation.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<TState, TEvent>(
    initialState: TState
) : ViewModel() {
    val state: State<TState> by lazy { mState }
    val event: Flow<TEvent> by lazy { mEvent.receiveAsFlow() }

    private val mState = mutableStateOf(initialState)

    private val mEvent = Channel<TEvent>(Channel.BUFFERED)

    protected val stateValue
        get() = mState.value

    protected fun updateState(modifier: TState.() -> TState) {
        mState.value = mState.value.modifier()
    }

    protected fun event(event: TEvent) {
        viewModelScope.launch {
            mEvent.send(event)
        }
    }
}

package com.example.todolistmvi.domain

sealed class EventState

data class FirstCounterState(val counter: Int) : EventState()

data class SecondCounterState(val counter: Int) : EventState()

data class ErrorState(val errorMessage: Throwable) : EventState()
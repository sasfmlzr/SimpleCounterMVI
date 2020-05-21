package com.example.todolistmvi.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistmvi.domain.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val getCounterUseCase = GetCounterUseCase()

    val counter: MutableLiveData<Int> = MutableLiveData(0)
    val secondCounter: MutableLiveData<Int> = MutableLiveData(0)

    private var initJob: Job

    init {
        initJob = viewModelScope.launch {
            getCounterUseCase.getCounter().catch {
                emit(ErrorState(it))
            }.collect {
                render(it)
            }

            getCounterUseCase.getCounter().catch {
                emit(ErrorState(it))
            }.collect {
                render(it)
            }
        }
    }

    val cancelJobClickListener = View.OnClickListener {
        viewModelScope.launch {
            if (!initJob.isCancelled) {
                initJob.cancel()
            }
        }
    }

    private fun render(eventState: EventState) {
        when (eventState) {
            is FirstCounterState -> counter.value = eventState.counter
            is SecondCounterState -> secondCounter.value = eventState.counter
            is ErrorState -> counter.value = 999999 // it will be some error state
        }
    }
}

package com.example.todolistmvi.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object SomeRepository {

    var counter = 0

    suspend fun getCounter(): Flow<EventState> {
        return flow {
            //counter = 0
            repeat(100) {
                counter += 1
                if (counter < 100) {
                    emit(FirstCounterState(counter))
                } else {
                    emit(SecondCounterState(counter))
                }
                delay(100)
            }
            throw RuntimeException("Finish")
        }
    }
}

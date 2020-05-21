package com.example.todolistmvi.domain

import kotlinx.coroutines.flow.Flow

class GetCounterUseCase {
    private val someRepository = SomeRepository

    suspend fun getCounter(): Flow<EventState> {
        return SomeRepository.getCounter()
    }
}
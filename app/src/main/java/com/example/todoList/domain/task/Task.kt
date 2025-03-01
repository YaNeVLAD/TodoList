package com.example.todoList.domain.task

import java.util.Date
import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = "",
    val expiration: Date? = null,
    val list: String = "",
    val category: String = "",
    val isCompleted: Boolean = false,
)
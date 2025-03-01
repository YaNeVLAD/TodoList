package com.example.todoList.presentation.list.data

import com.example.todoList.domain.task.Task

data class TaskItem(
    val task: Task,
    val isTomorrow: Boolean = false,
    val isNextWeek: Boolean = false,
)

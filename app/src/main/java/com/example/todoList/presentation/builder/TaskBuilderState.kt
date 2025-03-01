package com.example.todoList.presentation.builder

import com.example.todoList.domain.task.Task

data class TaskBuilderState(
    val task: Task = Task()
)
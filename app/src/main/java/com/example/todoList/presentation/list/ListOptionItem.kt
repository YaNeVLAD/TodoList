package com.example.todoList.presentation.list

import com.example.todoList.domain.task.DefaultLists

data class DefaultListOptionItem(
    val name: DefaultLists = DefaultLists.DEFAULT,
    val tasksCount: Int = 0,
)

data class ListOptionItem(
    val name: String = "",
    val tasksCount: Int = 0,
)
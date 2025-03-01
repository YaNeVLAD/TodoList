package com.example.todoList.presentation.list

import com.example.todoList.presentation.list.data.CategoryItem
import com.example.todoList.presentation.list.data.ListItem
import com.example.todoList.presentation.list.data.TaskItem

data class HeaderState(
    val search: String = "",
    val lists: List<ListItem> = emptyList(),
    val categories: List<CategoryItem> = emptyList(),
    val isListMenuVisible: Boolean = false,
)

data class TaskListState(
    val header: HeaderState = HeaderState(),
    val allTasks: List<TaskItem> = emptyList(),
    val tasksToShow: List<TaskItem> = emptyList(),
)

package com.example.todoList.presentation.list

import com.example.todoList.presentation.list.data.CategoryItem
import com.example.todoList.presentation.list.data.ListItem
import com.example.todoList.presentation.list.data.TaskItem

data class HeaderState(
    val lists: List<ListItem> = emptyList(),
    val categories: List<CategoryItem> = emptyList(),
    val isListMenuVisible: Boolean = false,
    val selectedList: ListItem? = null,
    val selectedCategory: CategoryItem? = null,
)

data class TaskListState(
    val search: String = "",
    val header: HeaderState = HeaderState(),
    val allTasks: List<TaskItem> = emptyList(),
    val tasksToShow: List<TaskItem> = emptyList(),
)

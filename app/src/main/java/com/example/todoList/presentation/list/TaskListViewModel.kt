package com.example.todoList.presentation.list

import com.example.todoList.domain.list.GetCategoriesUseCase
import com.example.todoList.domain.list.GetListsUseCase
import com.example.todoList.domain.list.GetTasksUseCase
import com.example.todoList.domain.task.DefaultLists
import com.example.todoList.presentation.common.BaseViewModel
import com.example.todoList.presentation.list.data.ListItem

class TaskListViewModel(
    private val getCategories: GetCategoriesUseCase,
    private val getLists: GetListsUseCase,
    private val getTasks: GetTasksUseCase
) : BaseViewModel<TaskListState, Any>(
    initialState = TaskListState()
) {
    private var mInitialized = false

    fun onToggleListMenu() {
        updateState {
            copy(header = header.copy(isListMenuVisible = !header.isListMenuVisible))
        }
    }

    fun filterTasks(list: ListItem) {
        updateState {
            copy(tasksToShow = allTasks.filter { it.task.list == list.name })
        }
    }

    fun initState() {
        if (mInitialized) {
            return
        }

        val tasks = getTasks()

        updateState {
            copy(
                header = header.copy(
                    lists = getLists(),
                    categories = getCategories()
                ),
                allTasks = tasks,
                tasksToShow = tasks.filter { it.task.list == DefaultLists.DEFAULT.name }
            )
        }

        mInitialized = true
    }
}
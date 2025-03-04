package com.example.todoList.presentation.list

import com.example.todoList.domain.list.GetCategoriesUseCase
import com.example.todoList.domain.list.GetListsUseCase
import com.example.todoList.domain.list.GetTasksUseCase
import com.example.todoList.domain.task.DefaultLists
import com.example.todoList.presentation.common.BaseViewModel
import com.example.todoList.presentation.list.data.ListItem
import com.example.todoList.presentation.list.data.TaskItem

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

    fun onTaskSelect(item: TaskItem) {
        updateState {
            copy(tasksToShow = tasksToShow.map {
                when {
                    it.task.id == item.task.id -> it.copy(isSelected = !it.isSelected)
                    else -> it
                }
            })
        }
    }

    fun onSearchChange(text: String) {
        updateState {
            copy(
                search = text,
                tasksToShow = when (text) {
                    "" -> allTasks.filter { it.task.list == header.selectedList?.name }
                    else -> allTasks.filter { it.task.title == text }
                }
            )
        }
    }

    fun filterTasks(list: ListItem) {
        updateState {
            copy(
                header = header.copy(selectedList = list),
                tasksToShow = allTasks.filter { it.task.list == list.name }
            )
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
                    categories = getCategories(),
                ),
                allTasks = tasks,
                tasksToShow = tasks.filter { it.task.list == DefaultLists.DEFAULT.name }
            )
        }

        mInitialized = true
    }
}
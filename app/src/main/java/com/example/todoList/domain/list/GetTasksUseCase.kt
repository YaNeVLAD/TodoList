package com.example.todoList.domain.list

import com.example.todoList.domain.task.DefaultLists
import com.example.todoList.domain.task.Task
import com.example.todoList.presentation.list.data.TaskItem

class GetTasksUseCase {
    operator fun invoke(): List<TaskItem> =
        listOf(
            TaskItem(
                Task(
                    list = DefaultLists.DEFAULT.name,
                    title = "1",
                )
            ),
            TaskItem(
                Task(
                    list = DefaultLists.DEFAULT.name,
                    title = "2",
                )
            ),
            TaskItem(
                Task(
                    list = DefaultLists.WORK.name,
                    title = "3",
                )
            ),
            TaskItem(
                Task(
                    list = DefaultLists.PRIVATE.name,
                    title = "4",
                )
            )
        )
}
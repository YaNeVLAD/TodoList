package com.example.todoList.domain.list

import com.example.todoList.domain.task.DefaultLists
import com.example.todoList.presentation.list.data.ListItem

class GetListsUseCase {
    operator fun invoke(): List<ListItem> {
        val default = DefaultLists.entries.map { it.name }
        val user: List<String> = emptyList()

        return (default + user).map {
            ListItem(name = it)
        }
    }
}

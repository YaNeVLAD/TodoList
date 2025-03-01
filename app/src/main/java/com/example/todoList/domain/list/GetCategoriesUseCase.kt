package com.example.todoList.domain.list

import com.example.todoList.domain.task.DefaultCategories
import com.example.todoList.presentation.list.data.CategoryItem

class GetCategoriesUseCase {
    operator fun invoke(): List<CategoryItem> {
        val default = DefaultCategories.entries.map { it.name }
        val user: List<String> = emptyList()

        return (default + user).map {
            CategoryItem(it)
        }
    }
}

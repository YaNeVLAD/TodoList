package com.example.todoList.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoList.domain.list.GetCategoriesUseCase
import com.example.todoList.domain.list.GetListsUseCase
import com.example.todoList.domain.list.GetTasksUseCase
import com.example.todoList.presentation.list.HeaderState
import com.example.todoList.presentation.list.TaskListViewModel
import com.example.todoList.presentation.list.data.ListItem
import com.example.todoList.ui.theme.Pink40
import com.example.todoList.ui.theme.PurpleGrey40

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel,
    navigateToBuilder: () -> Unit,
) {
    val state by viewModel.state

    viewModel.initState()

    Column(
        modifier = Modifier
            .background(PurpleGrey40)
            .fillMaxSize()
    ) {
        Header(
            state = state.header,
            toggleMenu = viewModel::onToggleListMenu,
            onFilterClick = viewModel::filterTasks
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(state.tasksToShow) {
                Text(text = it.task.title)
            }
        }
    }

}

@Composable
private fun DrawListItems(
    items: List<ListItem>,
    onClick: (ListItem) -> Unit,
) {
    items.map {
        val displayedCount: String = when (it.itemsCount) {
            0 -> ""
            else -> it.itemsCount.toString()
        }

        Row(
            modifier = Modifier
                .clickable { onClick(it) }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = "ListIcon",
                modifier = Modifier
            )
            Text(
                text = it.name,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )
            Text(
                text = displayedCount,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun Header(
    state: HeaderState,
    toggleMenu: () -> Unit,
    onFilterClick: (ListItem) -> Unit,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .background(Pink40)
            .fillMaxWidth()
    ) {
        ListsFilter(
            isPopoverVisible = state.isListMenuVisible,
            filters = state.lists,
            toggleMenu = toggleMenu,
            onFilterClick = onFilterClick
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White,
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )
        }
    }
}

@Composable
private fun ListsFilter(
    filters: List<ListItem>,
    isPopoverVisible: Boolean,
    toggleMenu: () -> Unit,
    onFilterClick: (ListItem) -> Unit,
) {
    Button(
        onClick = toggleMenu,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            Color.Transparent,
            Color.White,
            Color.Transparent,
            Color.Transparent,
        ),
        shape = RectangleShape
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Selected",
            tint = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .size(24.dp)
        )
        Text(
            text = "Все списки",
            fontSize = 12.sp,
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Down",
            modifier = Modifier
                .padding(15.dp, 0.dp)
        )
    }

    DropdownMenu(
        expanded = isPopoverVisible,
        offset = DpOffset(25.dp, 0.dp),
        onDismissRequest = toggleMenu,
        modifier = Modifier
    ) {
        Column {
            DrawListItems(
                items = filters,
                onClick = onFilterClick
            )
        }
    }
}

@Preview
@Composable
fun ListPreview() {
    TaskListScreen(
        viewModel = TaskListViewModel(
            GetCategoriesUseCase(),
            GetListsUseCase(),
            GetTasksUseCase(),
        )
    ) {}
}
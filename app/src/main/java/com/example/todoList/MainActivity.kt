package com.example.todoList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoList.domain.list.GetCategoriesUseCase
import com.example.todoList.domain.list.GetListsUseCase
import com.example.todoList.domain.list.GetTasksUseCase
import com.example.todoList.presentation.list.TaskListViewModel
import com.example.todoList.ui.list.TaskListScreen
import com.example.todoList.ui.theme.AppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AppNavHost()
            }
        }
    }
}

@Serializable
object ListScreen


@Serializable
object BuilderScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ListScreen
    ) {
        composable<ListScreen> {
            TaskListScreen(
                viewModel = TaskListViewModel(
                    GetCategoriesUseCase(),
                    GetListsUseCase(),
                    GetTasksUseCase(),
                )
            ) {
                navController.navigate(BuilderScreen)
            }
        }

        composable<BuilderScreen> {
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    AppTheme {
        AppNavHost()
    }
}
package com.example.taskvmg2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.taskvmg2.splash.SplashScreen
import com.example.taskvmg2.ui.screen.TaskDetailScreen
import com.example.taskvmg2.ui.screen.TaskListScreen
import com.example.taskvmg2.ui.viewmodel.DetailViewModel
import com.example.taskvmg2.ui.viewmodel.TaskViewModel

@Composable
fun AppNavigation(modifier: Modifier)
{
    val navController = rememberNavController()

    val taskViewModel: TaskViewModel = viewModel()

    NavHost(navController = navController
        , startDestination = Splash,
        modifier = modifier)
    {

        composable<Splash>
        {
            SplashScreen(navController = navController)

        }

        composable<TaskList>
        {
            TaskListScreen(navController = navController,
                viewModel = taskViewModel)

        }

        composable<TaskDetail>{ backStackEntry ->
            val route = backStackEntry.toRoute<TaskDetail>()
            val detailViewModel: DetailViewModel = viewModel()
            TaskDetailScreen(navController = navController,
                taskId = route.taskId,
                viewModel = detailViewModel)
        }

    }
}
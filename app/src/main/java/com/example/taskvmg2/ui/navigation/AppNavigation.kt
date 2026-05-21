package com.example.taskvmg2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.taskvmg2.splash.SplashScreen
import com.example.taskvmg2.ui.screen.TaskDetailScreen
import com.example.taskvmg2.ui.screen.TaskListScreen

@Composable
fun AppNavigation(modifier: Modifier)
{
    val navController = rememberNavController()

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
            TaskListScreen(navController = navController)
        }

        composable<TaskDetail>{ backStackEntry ->
            val route = backStackEntry.toRoute<TaskDetail>()
            TaskDetailScreen(navController = navController,
                taskId = route.taskId)
        }

    }
}
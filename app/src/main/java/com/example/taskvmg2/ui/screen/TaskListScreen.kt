package com.example.taskvmg2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskvmg2.ui.navigation.TaskDetail
import com.example.taskvmg2.ui.viewmodel.TaskViewModel
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.setValue

@Composable
fun TaskListScreen(navController: NavController?,
                   viewModel: TaskViewModel = viewModel())
{
    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
       ,floatingActionButton = {
           FloatingActionButton(onClick = {
               navController?.navigate(TaskDetail(taskId = -1))
           }) {
               Icon(
                   imageVector = Icons.Default.Add,
                   contentDescription = "Agregar tarea"
               )
           }
       }
    )
    { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Lista de tareas",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (viewModel.tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay tareas registradas")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    items(viewModel.tasks.size) { index ->
                        val task = viewModel.tasks[index]
                        Card(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()
                        )
                        {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(task.id.toString())
                                Text(task.title)
                                Checkbox(
                                    checked = task.completed,
                                    onCheckedChange = {
                                        viewModel.toggleTask(task)
                                    }
                                )
                                // Creando el dropdown menu en donde se van a mostrar las opciones de eliminar y editar, usando documentacion: Menu AndroidDevelopers
                                var expanded by remember { mutableStateOf(false) }
                                Box {
                                    IconButton(onClick = { expanded = true }) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = "Opciones"
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Editar") },
                                            onClick = {
                                                expanded = false
                                                navController?.navigate(TaskDetail(taskId = task.id!!))
                                            },
                                            leadingIcon = {
                                                Icon(Icons.Default.Edit, contentDescription = null)
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Eliminar") },
                                            onClick = {
                                                expanded = false
                                                viewModel.removeTask(task)
                                            },
                                            leadingIcon = {
                                                Icon(Icons.Default.Delete, contentDescription = null)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskListScreen() {
    TaskListScreen(null)
}
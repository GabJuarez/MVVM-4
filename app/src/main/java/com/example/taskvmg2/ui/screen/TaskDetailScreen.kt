package com.example.taskvmg2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.taskvmg2.ui.viewmodel.DetailViewModel

@Composable
fun TaskDetailScreen(
    navController: NavController,
    taskId: Int?,
    viewModel: DetailViewModel = viewModel()
) {
    val taskState by viewModel.state.collectAsState()

    LaunchedEffect(taskId) {
        if (taskId != null && taskId != -1) {
            viewModel.loadTask(taskId)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = if (taskId == null || taskId == -1) "Nueva Tarea" else "Editar Tarea",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                OutlinedTextField(
                    value = taskState.id?.toString() ?: "",
                    onValueChange = {
                        // El ID normalmente no se edita manualmente si es autogenerado,
                        // pero lo dejamos como solo lectura o podrías añadir onIdChange en el VM
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("ID (Auto-generado)")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Tag,
                            contentDescription = null
                        )
                    },
                    singleLine = true,
                    enabled = false // Deshabilitado porque se genera solo
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                OutlinedTextField(
                    value = taskState.title,
                    onValueChange = {
                        viewModel.onTitleChange(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Título")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    }
                )
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text("Cancelar")
                    }
                    Button(
                       modifier = Modifier.weight(1f),
                       onClick = {
                            viewModel.saveTask()
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = null
                        )
                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )
                        Text("Guardar")
                    }
                }
            }
        }
    }
}
package com.example.taskvmg2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskvmg2.ui.repository.TaskRepository
import com.example.taskvmg2.ui.model.Task

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    var tasks by mutableStateOf(listOf<Task>())
        private set

    init {
        loadTasks()
    }

    fun loadTasks() {
        tasks = repository.getTasks()
    }

    fun removeTask(task: Task) {
        repository.removeTask(task)
        loadTasks()
    }

    fun toggleTask(task: Task) {
        repository.toggleTask(task)
        loadTasks()
    }
}
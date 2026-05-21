package com.example.taskvmg2.ui.repository

import com.example.taskvmg2.ui.model.Task

class TaskRepository {
    
    // Usamos companion object para que todas las instancias compartan la misma lista
    // Esto es temporal hasta que uses Inyección de Dependencias (Hilt/Koin) o un Singleton real
    companion object {
        private val tasks = mutableListOf<Task>(
            Task(1, "Task 1", false),
            Task(2, "Task 2", true),
            Task(3, "Task 3", false),
            Task(4, "Task 4", true),
            Task(5, "Task 5", false)
        )
    }

    fun getTasks(): List<Task> = tasks.toList()

    fun addTask(task: Task) = tasks.add(task)

    fun getTaskId(id: Int): Task? = tasks.find { it.id == id }

    fun removeTask(task: Task) = tasks.remove(task)

    fun toggleTask(task: Task) {
        val index = tasks.indexOf(task)
        if (index != -1) {
            tasks[index] = task.copy(completed = !task.completed)
        }
    }
    
    fun updateTask(updatedTask: Task) {
        val index = tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            tasks[index] = updatedTask
        }
    }
    
    fun editTask(task: Task, newTitle: String) {
        val index = tasks.indexOf(task)
        if (index != -1) {
            tasks[index] = task.copy(title = newTitle)
        }
    }
}

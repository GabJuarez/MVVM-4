package com.example.taskvmg2.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskvmg2.ui.model.Task
import com.example.taskvmg2.ui.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailViewModel : ViewModel() {
    private val repository = TaskRepository()
    
    private val _state = MutableStateFlow(Task())
    val state: StateFlow<Task> = _state.asStateFlow()

    fun onTitleChange(newTitle: String) {
        _state.update { it.copy(title = newTitle) }
    }

    fun onCompletedChange(newCompleted: Boolean) {
        _state.update { it.copy(completed = newCompleted) }
    }


    fun loadTask(taskId: Int) {
        repository.getTaskId(taskId)?.let { task ->
            _state.value = task
        }
    }

    fun saveTask() {
        val currentTask = _state.value
        if (currentTask.title.isNotBlank()) {
            if (currentTask.id == null) {
                val newTask = currentTask.copy(id = (100..999).random())
                repository.addTask(newTask)
            } else {
                repository.updateTask(currentTask)
            }
            _state.value = Task()
        }
    }
}

package com.bia_technologies.bia.presentation.ui.screens.task_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bia_technologies.bia.domain.models.TaskModel
import com.bia_technologies.bia.domain.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _taskList = MutableLiveData<List<TaskModel>>()
    val taskList: LiveData<List<TaskModel>> = _taskList

    init {
        viewModelScope.launch {
            getTaskList()
        }
    }

    private suspend fun getTaskList() {
        _taskList.value = orderRepository.getTaskList()
    }
}
package com.bia_technologies.bia.domain.repositories

import com.bia_technologies.bia.domain.models.TaskModel

interface OrderRepository {

    suspend fun getTaskList(): List<TaskModel>
}
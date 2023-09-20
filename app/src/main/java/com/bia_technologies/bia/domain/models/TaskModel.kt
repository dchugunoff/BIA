package com.bia_technologies.bia.domain.models

import javax.inject.Inject

data class TaskModel @Inject constructor(
    val id: Int,
    val currentOrder: Boolean,
    val completedOrder: Boolean,
    val type: String,
    val city: String,
    val orderDate: String,
    val arrivalTime: String,
    val startingPoint: String,
    val endPoint: String,
    val carBodyType: String,
    val orderDetails: String,
    val paymentOptions: String,
    val contactPhone: String,
    val contactFullName: String
)
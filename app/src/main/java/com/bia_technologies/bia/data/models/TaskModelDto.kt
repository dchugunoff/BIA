package com.bia_technologies.bia.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class TaskModelDto @Inject constructor(
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
): Parcelable
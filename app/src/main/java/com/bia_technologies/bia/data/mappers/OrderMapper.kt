package com.bia_technologies.bia.data.mappers

import com.bia_technologies.bia.data.models.TaskModelDto
import com.bia_technologies.bia.domain.models.TaskModel
import javax.inject.Inject

class OrderMapper @Inject constructor() {

    fun mapDtoToEntity(orderDto: TaskModelDto) = TaskModel(
        currentOrder = orderDto.currentOrder,
        completedOrder = orderDto.completedOrder,
        type = orderDto.type,
        city = orderDto.city,
        orderDate = orderDto.orderDate,
        arrivalTime = orderDto.arrivalTime,
        startingPoint = orderDto.startingPoint,
        endPoint = orderDto.endPoint,
        carBodyType = orderDto.carBodyType,
        orderDetails = orderDto.orderDetails,
        paymentOptions = orderDto.paymentOptions,
        contactPhone = orderDto.contactPhone,
        contactFullName = orderDto.contactFullName,
        id = orderDto.id
    )

    fun convertTaskModelToDto(taskModel: TaskModel): TaskModelDto {
        return TaskModelDto(
            currentOrder = taskModel.currentOrder,
            completedOrder = taskModel.completedOrder,
            type = taskModel.type,
            city = taskModel.city,
            orderDate = taskModel.orderDate,
            arrivalTime = taskModel.arrivalTime,
            startingPoint = taskModel.startingPoint,
            endPoint = taskModel.endPoint,
            carBodyType = taskModel.carBodyType,
            orderDetails = taskModel.orderDetails,
            paymentOptions = taskModel.paymentOptions,
            contactPhone = taskModel.contactPhone,
            contactFullName = taskModel.contactFullName,
            id = taskModel.id
        )
    }
}
package com.bia_technologies.bia.data.repositories

import com.bia_technologies.bia.data.mappers.OrderMapper
import com.bia_technologies.bia.data.models.TaskModelDto
import com.bia_technologies.bia.domain.models.TaskModel
import com.bia_technologies.bia.domain.repositories.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val mapper: OrderMapper
) : OrderRepository {

    private val taskList = listOf(
        TaskModelDto(
            id = 1,
            currentOrder = true,
            completedOrder = false,
            type = "Мебель",
            city = "Москва",
            orderDate = "11.08.2023",
            arrivalTime = "12:00",
            startingPoint = "Склад 51, Ул. Пушкина 124Б",
            endPoint = "Склад 38, Ул. Розенбаума 89",
            carBodyType = "Тентованный",
            orderDetails = "Прописанные детали заказа",
            paymentOptions = "Прописанные параметры по оплате",
            contactPhone = "+7 800 896 52 63",
            contactFullName = "Иванов Владимир Иосифович",
        ),
        TaskModelDto(
            id = 2,
            currentOrder = false,
            completedOrder = false,
            type = "Мебель",
            city = "Москва",
            orderDate = "11.08.2023",
            arrivalTime = "14:00",
            startingPoint = "Склад 1, Ул. Комсомольская 384",
            endPoint = "Склад 11, ул. Радищева 145В",
            carBodyType = "Тентованный",
            orderDetails = "Прописанные детали заказа",
            paymentOptions = "Прописанные параметры по оплате",
            contactPhone = "+7 800 999 65 55",
            contactFullName = "Петров Петр Петрович",
        ),
        TaskModelDto(
            id = 3,
            currentOrder = false,
            completedOrder = true,
            type = "Мебель",
            city = "Москва",
            orderDate = "10.08.2023",
            arrivalTime = "12:00",
            startingPoint = "Склад 11, Ул. Ленина 123",
            endPoint = "Склад 1, ул. Карбышева 121Г",
            carBodyType = "Тентованный",
            orderDetails = "Прописанные детали заказа",
            paymentOptions = "Прописанные параметры по оплате",
            contactPhone = "+7 999 555 42 24",
            contactFullName = "Сергеев Сергей Сергеевич",
        )

    )

    override suspend fun getTaskList(): List<TaskModel> {
        return taskList.map { mapper.mapDtoToEntity(it) }
    }

    override suspend fun getTaskById(id: Int): TaskModel? {
        return taskList.find { it.id == id }?.let { mapper.mapDtoToEntity(it) }
    }


}
package com.bia_technologies.bia.domain.models

data class UserModel(
    val name: String,
    val phoneNumber: String,
    val userPhoto: Int,
    val profession: String,
    val personnelNumber: Int,
    val citizenship: String,
    val typeCar: String,
    val carRegistrationNumber: String
)
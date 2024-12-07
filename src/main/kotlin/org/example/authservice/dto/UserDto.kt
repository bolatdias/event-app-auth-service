package org.example.authservice.dto

import org.example.authservice.model.User


class UserPayload(
    val user: User,
    val message: String
)
package org.example.authservice.utils

import org.example.authservice.dto.SignUpRequest
import org.example.authservice.model.User


fun mapToUserModel(request: SignUpRequest): User {
    return User(
        username = request.username,
        email = request.email,
        password = request.password
    )
}
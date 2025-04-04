package org.example.authservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import lombok.Getter
import org.example.authservice.model.RoleName


@Getter
data class Token(
    val accessToken: String,
    val tokenType: String = "Bearer"
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SignUpRequest(
    @field:Size(min = 3, max = 15)
    @field:NotBlank(message = "Username is required")
    var username: String,

    @field:Size(max = 40)
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Invalid email format")
    var email: String,

    @field:Size(min = 6, max = 20)
    @field:NotBlank(message = "Password is required")
    var password: String,

    @Enumerated(EnumType.STRING)
    var role: RoleName,
)


@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginInRequest(
    @NotBlank
    var usernameOrEmail: String,
    @NotBlank
    var password: String
)
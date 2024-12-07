package org.example.authservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.Getter
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Getter
data class Token(
    val accessToken: String,
    val tokenType: String = "Bearer"
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SignUpRequest(
    @Size(min = 4, max = 40)
    var name: @NotBlank String,
    @Size(min = 3, max = 15)
    var username: @NotBlank String,
    @Size(max = 40)
    var email: @NotBlank @Email String,
    @Size(min = 6, max = 20)
    var password: @NotBlank String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginInRequest(
    @NotBlank
    var username: String,
    @NotBlank
    var password: String
)
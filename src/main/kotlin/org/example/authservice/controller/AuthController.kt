package org.example.authservice.controller


import lombok.RequiredArgsConstructor
import org.example.authservice.dto.LoginInRequest
import org.example.authservice.dto.SignUpRequest
import org.example.authservice.dto.Token
import org.example.authservice.dto.UserPayload
import org.example.authservice.service.AuthService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): UserPayload {
        return authService.signUp(request)
    }

    @PostMapping("/login-in")
    fun loginIn(@RequestBody request: LoginInRequest): Token {
        return authService.authenticateUser(request)
    }

    @GetMapping("/test")
    fun get(): String {
        return "dias"
    }
}
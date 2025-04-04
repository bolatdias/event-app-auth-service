package org.example.authservice.controller



import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.example.authservice.dto.LoginInRequest
import org.example.authservice.dto.SignUpRequest
import org.example.authservice.dto.Token
import org.example.authservice.dto.UserPayload
import org.example.authservice.service.AuthService
import org.example.authservice.service.UserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth-service/auth")
@RequiredArgsConstructor
class AuthController(
    private val authService: AuthService,
    private val userService: UserService
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid request: SignUpRequest): UserPayload {
        return authService.signUp(request)
    }

    @PostMapping("/login-in")
    fun loginIn(@RequestBody @Valid request: LoginInRequest): Token {
        return authService.authenticateUser(request)
    }

    @GetMapping("/check-username-availability")
    fun checkUsernameAvailability(username: String?): Boolean {
        return userService.checkUsernameAvailability(username)
    }

    @GetMapping("/check-email-availability")
    fun checkEmailAvailability(email: String?): Boolean {
        return userService.checkEmailAvailability(email)
    }

}
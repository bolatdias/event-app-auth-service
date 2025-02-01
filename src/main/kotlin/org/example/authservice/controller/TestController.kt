package org.example.authservice.controller

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import lombok.RequiredArgsConstructor
import org.example.authservice.model.User
import org.example.authservice.security.CurrentUser
import org.example.authservice.security.UserPrincipal
import org.example.authservice.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class TestController (
    private val userService: UserService
)
{
    @GetMapping("/get-current-user")
    fun getCurrentUser(@CurrentUser currentUser: UserPrincipal) : User {
        return userService.getCurrentUser(currentUser)
    }
}
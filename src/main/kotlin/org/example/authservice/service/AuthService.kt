package org.example.authservice.service


import lombok.RequiredArgsConstructor
import org.example.authservice.dto.LoginInRequest
import org.example.authservice.dto.SignUpRequest
import org.example.authservice.dto.Token
import org.example.authservice.dto.UserPayload
import org.example.authservice.model.RoleName
import org.example.authservice.model.User
import org.example.authservice.model.exception.ResourceNotFoundException
import org.example.authservice.repository.RoleRepository
import org.example.authservice.repository.UserRepository
import org.example.authservice.security.JwtTokenProvider
import org.example.authservice.utils.mapToUserModel
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: JwtTokenProvider,
) {
    fun authenticateUser(request: LoginInRequest): Token {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )

        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = tokenProvider.generateToken(authentication)

        return Token(jwt)
    }

    fun signUp(request: SignUpRequest): UserPayload {
        val user = mapToUserModel(request)
        insertUser(user, RoleName.ROLE_USER)

        return UserPayload(user, "success")
    }

    private fun insertUser(user: User, role: RoleName) {
        user.password = passwordEncoder.encode(user.password)

        val userRole = roleRepository.findByName(role)
            .orElseThrow { ResourceNotFoundException("Role", "userRole", role.name) }

        user.roles.add(userRole)
        userRepository.save(user)
    }


}
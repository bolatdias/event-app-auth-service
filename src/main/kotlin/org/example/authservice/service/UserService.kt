package org.example.authservice.service


import lombok.RequiredArgsConstructor
import org.example.authservice.model.User
import org.example.authservice.repository.RoleRepository
import org.example.authservice.repository.UserRepository
import org.example.authservice.security.UserPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleRepository: RoleRepository
) {

    fun checkUsernameAvailability(username: String?): Boolean {
        return !userRepository.existsByUsername(username)
    }

    fun checkEmailAvailability(email: String?): Boolean {
        return !userRepository.existsByEmail(email)
    }

    fun getCurrentUser(currentUser: UserPrincipal): User {
        return userRepository.findById(currentUser.id).orElseThrow()
    }

    val currentUser: User
        get() {
            val authentication = SecurityContextHolder.getContext().authentication

            val userPrincipal: UserPrincipal = authentication.principal as UserPrincipal

            val userId: Long = userPrincipal.id

            return userRepository.findById(userId)
                .orElseThrow { RuntimeException("User not found with id: $userId") }
        }
}
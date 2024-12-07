package org.example.authservice.security


import org.example.authservice.model.User
import org.example.authservice.model.exception.ResourceNotFoundException
import org.example.authservice.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomUserDetailsService(
    var userRepository: UserRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(usernameOrEmail: String): UserDetails {
        val user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow { UsernameNotFoundException("UserController not found with username or email : $usernameOrEmail") }

        return UserPrincipal.create(user)
    }

    @Transactional
    fun loadUserById(id: Long): UserDetails {
        val user: User = userRepository.findById(id).orElseThrow {
            ResourceNotFoundException(
                "UserController",
                "id",
                id
            )
        }

        return UserPrincipal.create(user)
    }
}
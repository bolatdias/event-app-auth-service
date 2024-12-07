package org.example.authservice.repository


import org.example.authservice.model.Role
import org.example.authservice.model.RoleName
import org.example.authservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String?): Optional<User>

    fun findByUsernameOrEmail(username: String?, email: String?): Optional<User>

    fun findByIdIn(userIds: List<Long>): List<User>

    fun findByUsername(username: String?): Optional<User>

    fun existsByUsername(username: String?): Boolean

    fun existsByEmail(email: String?): Boolean
}


interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(roleName: RoleName?): Optional<Role>
}
package org.example.authservice.model

import jakarta.persistence.*
import org.hibernate.annotations.NaturalId


@Entity
@Table(name = "roles")
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    var name: RoleName? = null
)

enum class RoleName {
    ROLE_USER,
    ROLE_RESTAURANT_OWNER,
    ROLE_SERVICE_VENDOR
}
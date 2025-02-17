package org.example.authservice.model

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import org.example.authservice.model.audit.DateAudit
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(
    name = "users",
    uniqueConstraints =
    [UniqueConstraint(columnNames = ["username"]), UniqueConstraint(columnNames = ["email"])]
)
@Data
@NoArgsConstructor
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Size(max = 15) @NotBlank
    val username: String,

    @Email
    @Size(max = 40) @NotBlank
    @Column(unique = true)
    val email: String,

    @Size(max = 100) @NotBlank
    var password: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: MutableSet<Role> = mutableSetOf(),
)


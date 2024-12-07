package org.example.authservice.model.audit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import lombok.Getter
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.*


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(value = ["createdAt", "updatedAt"], allowGetters = true)
@Getter
abstract class DateAudit : Serializable {
    @CreatedDate
    private val createdAt: Date? = null

    @LastModifiedDate
    private val updatedAt: Date? = null
}
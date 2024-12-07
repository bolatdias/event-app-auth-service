package org.example.authservice.model.audit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy


@MappedSuperclass
@JsonIgnoreProperties(value = ["createdBy", "updatedBy"], allowGetters = true)
abstract class UserDateAudit : DateAudit() {
    @CreatedBy
    var createdBy: Long? = null
    @LastModifiedBy
    var updatedBy: Long? = null
}
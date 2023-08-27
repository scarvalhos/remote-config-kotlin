package com.sam.remoteconfig.repositories

import com.sam.remoteconfig.domain.entities.CollaboratorEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Repository
@Transactional
interface CollaboratorRepository : JpaRepository<CollaboratorEntity, UUID> {
    @Transactional
    fun findByEmail (email: String): UserDetails?
    abstract fun findById(id: String): Optional<CollaboratorEntity>
}

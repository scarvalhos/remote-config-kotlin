package com.sam.remoteconfig.repositories

import com.sam.remoteconfig.domain.entities.RemoteConfigEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional
interface RemoteConfigRepository : JpaRepository<RemoteConfigEntity, UUID> {
    @Transactional
    fun findByKey (key: String): RemoteConfigEntity?
}

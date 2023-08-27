package com.sam.remoteconfig.repositories

import com.sam.remoteconfig.domain.entities.AccessTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Transactional
interface AccessTokenRepository : JpaRepository<AccessTokenEntity, UUID>

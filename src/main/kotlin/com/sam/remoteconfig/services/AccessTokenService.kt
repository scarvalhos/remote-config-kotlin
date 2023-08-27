package com.sam.remoteconfig.services

import com.sam.remoteconfig.domain.entities.AccessTokenEntity
import com.sam.remoteconfig.domain.entities.CollaboratorEntity
import com.sam.remoteconfig.repositories.AccessTokenRepository
import com.sam.remoteconfig.security.TokenService
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.ZoneId

@Service
@Transactional
class AccessTokenService (
    private val accessTokenRepository: AccessTokenRepository,
    private val tokenService: TokenService,
){
    fun create(collaborator: CollaboratorEntity): AccessTokenEntity {
        val accessTokenEntity = AccessTokenEntity()

        accessTokenEntity.apply {
            this.token = tokenService.generateToken(collaborator)
            this.createdAt = getDate()
            this.updatedAt = getDate()
        }

        accessTokenRepository.save(accessTokenEntity)
        return accessTokenEntity
    }

    fun list(): List<AccessTokenEntity> = accessTokenRepository.findAll()

    private fun getDate(): String? {
        val dataHoraConcatenada = LocalDateTime.now().
        atZone(ZoneId.of("America/Sao_Paulo"))
        return dataHoraConcatenada.format(
            DateTimeFormatter.
            ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}
package com.sam.remoteconfig.services

import com.sam.remoteconfig.domain.dtos.RemoteConfigDTO
import com.sam.remoteconfig.domain.entities.RemoteConfigEntity
import com.sam.remoteconfig.repositories.RemoteConfigRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Service
@Transactional
class RemoteConfigService (private val remoteConfigRepository: RemoteConfigRepository) {
    fun create (remoteConfigDTO: RemoteConfigDTO): RemoteConfigEntity {
        println("START - CREATING A NEW COLLABORATOR")

        val remoteConfig = remoteConfigRepository.findByKey(remoteConfigDTO.key)

        if (remoteConfig != null) throw Exception("Configuração já cadastrada!")

        val remoteConfigEntity = RemoteConfigEntity()

        remoteConfigEntity.apply {
            this.key = remoteConfigDTO.key
            this.value = remoteConfigDTO.value
            this.type = remoteConfigDTO.type
            this.active = remoteConfigDTO.active
            this.createdAt = getDate()
            this.updatedAt = getDate()
        }

        remoteConfigRepository.save(remoteConfigEntity)
        return remoteConfigEntity
    }

    fun find (key: String): RemoteConfigEntity? = remoteConfigRepository.findByKey(key)

    fun list (): MutableList<RemoteConfigEntity> = remoteConfigRepository.findAll()

    fun delete (id: UUID) = remoteConfigRepository.deleteById(id)


    private fun getDate(): String? {
        val dateAndHour = LocalDateTime.now().
        atZone(ZoneId.of("America/Sao_Paulo"))
        return dateAndHour.format(
            DateTimeFormatter.
            ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}
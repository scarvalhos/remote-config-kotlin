package com.sam.remoteconfig.services

import com.sam.remoteconfig.domain.dtos.CollaboratorDTO
import com.sam.remoteconfig.domain.entities.CollaboratorEntity
import com.sam.remoteconfig.repositories.CollaboratorRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.UUID

@Service
@Transactional
class CollaboratorService (
    private val collaboratorRepository: CollaboratorRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun create (collaboratorDTO: CollaboratorDTO): CollaboratorEntity {
        println("START - CREATING A NEW COLLABORATOR")

        val collaborator = collaboratorRepository.findByEmail(collaboratorDTO.email)

        if (collaborator != null) {
            throw Exception("Colaborador já cadastrado!")
        }

        val collaboratorEntity = CollaboratorEntity(
            email = collaboratorDTO.email,
            name = collaboratorDTO.name,
            password = generatePassword(collaboratorDTO.password),
            avatar = collaboratorDTO.avatar,
            createdAt = getDate(),
            updatedAt = getDate()
        )

        collaboratorRepository.save(collaboratorEntity)
        return collaboratorEntity
    }

    fun find (id: UUID): CollaboratorEntity? {
        return collaboratorRepository.findById(id).get()
    }

    fun findByIdString (id: String): CollaboratorEntity? {
        return collaboratorRepository.findById(id).get()
    }

    fun list (): MutableList<CollaboratorEntity> {
        return collaboratorRepository.findAll()
    }

    fun delete (id: UUID): String {
        collaboratorRepository.deleteById(id)
        return "Colaborador excluído com sucesso."
    }

    fun update (id: UUID): CollaboratorEntity? {
        return collaboratorRepository.findById(id).get()
    }

    private fun generatePassword(random: String): String {
        return bCryptPasswordEncoder.encode(random)
    }

    private fun getDate(): String? {
        val dataHoraConcatenada = LocalDateTime.now().
        atZone(ZoneId.of("America/Sao_Paulo"))
        return dataHoraConcatenada.format(
            DateTimeFormatter.
            ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}
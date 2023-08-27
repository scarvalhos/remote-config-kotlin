package com.sam.remoteconfig.controllers

import com.sam.remoteconfig.domain.dtos.ResponseDTO
import com.sam.remoteconfig.domain.entities.CollaboratorEntity
import com.sam.remoteconfig.services.CollaboratorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/collaborator")
class CollaboratorController (private val collaboratorService: CollaboratorService) {
    @GetMapping("/{id}")
    fun find(@PathVariable("id") id: UUID):  ResponseEntity<ResponseDTO<CollaboratorEntity>> {
        val collaborator = collaboratorService.find(id)
        return sendResponse<CollaboratorEntity>( "Colaborador encontrado com sucesso!", collaborator!!)
    }

    @GetMapping()
    fun list():  ResponseEntity<ResponseDTO<MutableList<CollaboratorEntity>>> {
        val collaborators = collaboratorService.list()
        return sendResponse<MutableList<CollaboratorEntity>>( "Colaboradores encontrados com sucesso!", collaborators)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: UUID): ResponseEntity<ResponseDTO<String>> {
        val response = collaboratorService.delete(id)
        return sendResponse<String>( message = response.toString(), data = "")
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: UUID):  ResponseEntity<ResponseDTO<CollaboratorEntity>> {
        val collaborator = collaboratorService.update(id)
        return sendResponse<CollaboratorEntity>( message = "Colaborador atualizado com sucesso!", data = collaborator!!)
    }

    private fun <T,> sendResponse(message: String, data: T): ResponseEntity<ResponseDTO<T>> {
        val response = ResponseDTO<T>(message, data)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}
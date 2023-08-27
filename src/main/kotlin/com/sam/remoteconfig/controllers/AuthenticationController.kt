package com.sam.remoteconfig.controllers

import com.sam.remoteconfig.domain.dtos.AuthenticationDTO
import com.sam.remoteconfig.domain.dtos.CollaboratorDTO
import com.sam.remoteconfig.domain.dtos.ResponseDTO
import com.sam.remoteconfig.domain.entities.CollaboratorEntity
import com.sam.remoteconfig.services.CollaboratorService
import com.sam.remoteconfig.security.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val manager: AuthenticationManager,
    private val tokenService: TokenService,
    private val collaboratorService: CollaboratorService
) {
    @PostMapping("/login")
    fun login(@RequestBody authData: AuthenticationDTO): ResponseEntity<ResponseDTO<String>> {
        val token = UsernamePasswordAuthenticationToken(authData.email, authData.password)
        val authentication = manager.authenticate(token)
        val tokenGenerated = tokenService.generateToken(authentication.principal as CollaboratorEntity)
        return sendResponse<String>( "Login efetuado com sucesso!", tokenGenerated)
    }

    @PostMapping("/register")
    fun register(@RequestBody collaboratorDTO: CollaboratorDTO): ResponseEntity<ResponseDTO<CollaboratorEntity>> {
        val collaborator = collaboratorService.create(collaboratorDTO)
        return sendResponse<CollaboratorEntity>( "Colaborador criado com sucesso!", collaborator)
    }

    private fun <T,> sendResponse(message: String, data: T): ResponseEntity<ResponseDTO<T>> {
        val response = ResponseDTO<T>(message, data)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

}
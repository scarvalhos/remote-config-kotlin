package com.sam.remoteconfig.controllers

import com.sam.remoteconfig.domain.dtos.ResponseDTO
import com.sam.remoteconfig.domain.entities.AccessTokenEntity
import com.sam.remoteconfig.services.AccessTokenService
import com.sam.remoteconfig.services.CollaboratorService
import com.sam.remoteconfig.security.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/access-token")
class AccessTokenController (
    private val accessTokenService: AccessTokenService,
    private val collaboratorService: CollaboratorService,
    private val tokenService: TokenService,
) {
    @PostMapping()
    fun create(@RequestHeader authorization: String): ResponseEntity<ResponseDTO<AccessTokenEntity>> {
        val token = tokenService.getSubject(authorization.split(' ')[1])
        val collaborator = collaboratorService.findByIdString(token!!)
        val accessToken = accessTokenService.create(collaborator!!)
        return sendResponse<AccessTokenEntity>( "Token criado com sucesso!", accessToken)
    }

    @GetMapping()
    fun list(): ResponseEntity<ResponseDTO<List<AccessTokenEntity>>> {
        return sendResponse<List<AccessTokenEntity>>( "Tokens listados com sucesso!", accessTokenService.list())
    }

    private fun <T,> sendResponse(message: String, data: T): ResponseEntity<ResponseDTO<T>> {
        val response = ResponseDTO<T>(message, data)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}
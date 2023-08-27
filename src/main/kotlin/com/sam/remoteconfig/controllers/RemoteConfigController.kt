package com.sam.remoteconfig.controllers

import com.sam.remoteconfig.domain.dtos.ResponseDTO
import com.sam.remoteconfig.domain.dtos.RemoteConfigDTO
import com.sam.remoteconfig.domain.entities.RemoteConfigEntity
import com.sam.remoteconfig.services.RemoteConfigService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/remote-config")
class RemoteConfigController (
    private val remoteConfigService: RemoteConfigService,
) {
    @PostMapping()
    fun create(@RequestBody remoteConfigDTO: RemoteConfigDTO): ResponseEntity<ResponseDTO<RemoteConfigEntity>> {
        val remoteConfig = remoteConfigService.create(remoteConfigDTO)
        return sendResponse<RemoteConfigEntity>( "Configuração criada com sucesso!", remoteConfig)
    }

    @GetMapping()
    fun list(): ResponseEntity<ResponseDTO<MutableList<RemoteConfigEntity>>> {
        val remoteConfigs = remoteConfigService.list()
        return sendResponse<MutableList<RemoteConfigEntity>>( "Configurações listadas com sucesso!", remoteConfigs)
    }

    @GetMapping("/{key}")
    fun find(@PathVariable("key") key: String): ResponseEntity<ResponseDTO<RemoteConfigEntity>> {
        val remoteConfig = remoteConfigService.find(key)
        return sendResponse<RemoteConfigEntity>( "Configuração encontrada com sucesso!", remoteConfig!!)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: UUID): ResponseEntity<ResponseDTO<String>> {
        remoteConfigService.delete(id)
        return sendResponse<String>( "Configuração excluída com sucesso!", "")
    }

    private fun <T,> sendResponse(message: String, data: T): ResponseEntity<ResponseDTO<T>> {
        val response = ResponseDTO<T>(message, data)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}
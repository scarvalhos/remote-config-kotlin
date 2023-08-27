package com.sam.remoteconfig.domain.dtos

data class ResponseDTO <T>(
    val message: String,
    val data: T? = null
)
package com.sam.remoteconfig.domain.dtos

data class RemoteConfigDTO (
    val type: String,
    val key: String,
    val value: String,
    val active: Boolean
)
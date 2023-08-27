package com.sam.remoteconfig.domain.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "remoteConfigs", schema = "public")
class RemoteConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "type", nullable = false)
    var type: String? = null

    @Column(name = "key", unique = true, nullable = false)
    var key: String? = null

    @Column(name = "value")
    var value: String? = null

    @Column(name = "active", nullable = false)
    var active: Boolean? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: String? = null

    @Column(name = "updated_at", nullable = false)
    var updatedAt: String? = null
}

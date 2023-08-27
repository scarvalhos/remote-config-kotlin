package com.sam.remoteconfig.domain.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "accessTokens", schema = "public")
class AccessTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "token", nullable = false)
    var token: String? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: String? = null

    @Column(name = "updated_at", nullable = false)
    var updatedAt: String? = null
}

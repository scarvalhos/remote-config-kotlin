package com.sam.remoteconfig.security

import com.sam.remoteconfig.domain.entities.CollaboratorEntity
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.algorithms.Algorithm
import java.time.LocalDateTime
import java.time.ZoneOffset
import com.auth0.jwt.JWT
import java.time.Instant
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@Transactional
class TokenService( @Value("\${api.security.token.secret}") private val secret: String) {
    fun generateToken(user: CollaboratorEntity): String {
        val algorithm = Algorithm.HMAC256(secret)

        return JWT.create()
            .withIssuer("sofia/docencia")
            .withSubject(user.id)
            .withClaim("id", user.id)
            .withExpiresAt(getExpireDate())
            .sign(algorithm)
    }

    fun getSubject(tokenJWT: String?): String? {
        return try {
            val algorithm = Algorithm.HMAC256(secret)

            JWT.require(algorithm)
                .withIssuer("sofia/docencia")
                .build()
                .verify(tokenJWT)
                .subject
        } catch (exception: JWTVerificationException) {
            throw RuntimeException("Token JWT inválido ou expirado!")
        }
    }

    private fun getExpireDate(): Instant = LocalDateTime.now().plusHours(2L).toInstant(ZoneOffset.of("-03:00"))
}
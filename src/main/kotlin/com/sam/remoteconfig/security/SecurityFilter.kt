package com.sam.remoteconfig.security

import com.sam.remoteconfig.repositories.CollaboratorRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.ServletException
import jakarta.servlet.FilterChain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.io.IOException

@Component
class SecurityFilter : OncePerRequestFilter() {
    @Autowired
    private val tokenService: TokenService? = null

    @Autowired
    private val collaboratorRepository: CollaboratorRepository? = null

    @Throws(
        ServletException::class,
        IOException::class,
        ServletException::class,
        IOException::class
    )
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val tokenJWT = recoverToken(request)

        if (tokenJWT != null) {
            val subject = tokenService?.getSubject(tokenJWT)

            val collaborator = collaboratorRepository?.findByEmail(subject!!)

            val authentication =
                UsernamePasswordAuthenticationToken(collaborator, null, collaborator?.authorities)

            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        val authorizationHeader = request.getHeader("Authorization")
        return authorizationHeader?.replace("Bearer ", "")
    }
}
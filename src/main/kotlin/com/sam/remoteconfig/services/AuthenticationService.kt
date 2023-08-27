package com.sam.remoteconfig.services

import com.sam.remoteconfig.repositories.CollaboratorRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val collaboratorRepository: CollaboratorRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        return collaboratorRepository.findByEmail(username!!)
    }
}
package com.sam.remoteconfig.domain.entities

import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "collaborators", schema = "public")
data class CollaboratorEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null,
    private var password: String,
    private var avatar: String? = null,
    private var createdAt: String? = null,
    private var updatedAt: String? = null,
    @Column(name = "email", unique = true, length = 20)
    private var email: String,
    @Column(name = "name", length = 100)
    private var name: String,
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getUsername() = email

    override fun getPassword() = password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}

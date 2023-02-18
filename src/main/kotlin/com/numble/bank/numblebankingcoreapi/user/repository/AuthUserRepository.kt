package co.numble.bank.numblebankingapi.user.repository

import co.numble.bank.numblebankingapi.user.domain.AuthUser
import org.springframework.data.jpa.repository.JpaRepository

interface AuthUserRepository : JpaRepository<AuthUser, Long> {
    fun findByEmail(email: String): AuthUser ?
    fun findByUserToken(userToken: String): AuthUser ?
    fun existsAuthUserByEmail(email: String): Boolean
    fun existsAuthUserByUserToken(userToken: String): Boolean
}
package co.numble.bank.numblebankingapi.user.domain

import co.numble.bank.numblebankingapi.Common.BaseEntity
import co.numble.bank.numblebankingapi.Common.util.TokenGenerator
import co.numble.bank.numblebankingapi.user.friend.domain.Friend
import co.numble.bank.numblebankingapi.user.web.dto.AuthUserDto
import javax.persistence.*

@Entity
@Table(name = "users")
class AuthUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ? = null,

    @Column(name = "user_token", nullable = false, unique = true)
    val userToken: String = TokenGenerator.generateToken(AUTH_USER_PREFIX),

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authUser", cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    val friends: MutableList<Friend> = mutableListOf()
) : BaseEntity() {
    companion object{
        private const val AUTH_USER_PREFIX = "users_"
    }

    override fun toString(): String {
        return "AuthUser : id=$id, userToken: $userToken, email: $email, password: $password, createdAt: $createdAt"
    }

    fun toFriendInfo() : AuthUserDto.FriendInfo {
        return AuthUserDto.FriendInfo(
            email = email,
            userToken =  userToken
        )
    }
}
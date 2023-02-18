package co.numble.bank.numblebankingapi.user.friend.domain

import co.numble.bank.numblebankingapi.Common.BaseEntity
import co.numble.bank.numblebankingapi.Common.util.TokenGenerator
import co.numble.bank.numblebankingapi.user.domain.AuthUser
import javax.persistence.*

@Entity
@Table(name = "friends")
class Friend(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ? = null,

    @Column(name = "target_user_token", unique = true, nullable = false)
    val targetUserToken: String,

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val authUser: AuthUser

) : BaseEntity()
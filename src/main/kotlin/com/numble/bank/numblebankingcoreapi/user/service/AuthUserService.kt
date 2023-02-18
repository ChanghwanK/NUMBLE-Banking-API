package co.numble.bank.numblebankingapi.user.service

import co.numble.bank.numblebankingapi.Common.exception.DuplicatedEmailException
import co.numble.bank.numblebankingapi.Common.exception.EntityNotFoundException
import co.numble.bank.numblebankingapi.user.domain.AuthUser
import co.numble.bank.numblebankingapi.user.friend.domain.Friend
import co.numble.bank.numblebankingapi.user.friend.repository.FriendRepository
import co.numble.bank.numblebankingapi.user.repository.AuthUserRepository
import co.numble.bank.numblebankingapi.user.web.dto.AuthUserDto
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class AuthUserService(
    private val authUserRepository: AuthUserRepository,
    private val friendRepository: FriendRepository
) {
    private val logger = KotlinLogging.logger { }

    fun registerAuthUser(
      userRegisterRequest: AuthUserDto.AuthUserRegisterRequest
    ): AuthUserDto.AuthUserRegisterInfo {
        val email = userRegisterRequest.email

        if(authUserRepository.existsAuthUserByEmail(email)) {
            throw DuplicatedEmailException()
        }

        val authUser = AuthUser(
            email = email,
            password = userRegisterRequest.password
        )

        authUserRepository.save(authUser)

        return AuthUserDto.AuthUserRegisterInfo(
            email = authUser.email,
            userToken = authUser.userToken,
            registeredAt = authUser.createdAt.let {
                it?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            } !!
        )
    }

    fun registerUserFriend(userToken: String, requestUserDto: AuthUserDto.FriendRegisterRequest) {
        val storedAuthUser = authUserRepository.findByUserToken(userToken)
            ?: throw EntityNotFoundException()

        checkIsExistToken(requestUserDto.friendTargetUserToken)

        val friend = Friend(
            targetUserToken = requestUserDto.friendTargetUserToken,
            authUser = storedAuthUser
        )

        friendRepository.save(friend)
        storedAuthUser.friends.add(friend)
    }

    fun getAllFriends(userToken: String): List<AuthUserDto.FriendInfo> {
        val storedUser = authUserRepository.findByUserToken(userToken)
            ?: throw EntityNotFoundException()

        return storedUser.friends
            .map {
                val friendUser = authUserRepository.findByUserToken(it.targetUserToken)
                    ?: throw EntityNotFoundException()
                friendUser.toFriendInfo()
            }

    }

    private fun checkIsExistToken(token: String) {
        if(! authUserRepository.existsAuthUserByUserToken(token)) {
            logger.warn { "token: $token" }
            throw EntityNotFoundException()
        }
    }
}
package co.numble.bank.numblebankingapi.user.web.dto

class AuthUserDto {
    class AuthUserRegisterRequest(
        val email: String,
        val password: String
    ) {
        override fun toString(): String {
            return "AuthUserRegisterRequest: $email: email, password: $password"
        }
    }

    class AuthUserRegisterInfo(
        val email: String,
        val userToken: String,
        val registeredAt: String
    ) {
        override fun toString(): String {
            return "AuthUserRegisterInfo: email: $email, userToken: $userToken, registeredAt: $registeredAt"
        }
    }

    class FriendRegisterRequest(
        val friendTargetUserToken: String
    )

    class FriendInfo(
        val email: String,
        val userToken: String
    )
}
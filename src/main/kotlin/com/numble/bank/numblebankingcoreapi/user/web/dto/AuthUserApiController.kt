package co.numble.bank.numblebankingapi.user.web.dto

import co.numble.bank.numblebankingapi.user.service.AuthUserService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/auth")
@RestController
class AuthUserApiController(
    private val authUserService: AuthUserService
) {

    @PostMapping("")
    fun registerUser(
        @RequestBody authUserRegisterRequest: AuthUserDto.AuthUserRegisterRequest
    ) = ResponseEntity.ok(authUserService.registerAuthUser(authUserRegisterRequest))

    @PostMapping("/{userToken}/friend")
    fun registerFriend(
        @PathVariable userToken: String,
        @RequestBody registerFriendRequest: AuthUserDto.FriendRegisterRequest
    ) : ResponseEntity<Any> {
        authUserService.registerUserFriend(userToken, registerFriendRequest)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{userToken}")
    fun getUserAllFriends(
        @PathVariable userToken: String
    ) = ResponseEntity.ok(authUserService.getAllFriends(userToken))
}
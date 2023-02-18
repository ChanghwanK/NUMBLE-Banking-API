package co.numble.bank.numblebankingapi.user.friend.repository

import co.numble.bank.numblebankingapi.user.friend.domain.Friend
import org.springframework.data.jpa.repository.JpaRepository

interface FriendRepository : JpaRepository<Friend, Long>
package co.numble.bank.numblebankingapi.Common.util

import org.apache.commons.lang3.RandomStringUtils


class TokenGenerator {
    companion object {
        private const val TOKEN_LENGTH = 20
        fun generateToken(prefix: String): String {
            return generateToken(prefix, TOKEN_LENGTH)
        }

        fun generateToken(prefix: String,  length: Int) : String {
            return prefix + RandomStringUtils.randomAlphabetic(length)
        }
    }
}
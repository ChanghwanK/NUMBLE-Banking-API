package co.numble.bank.numblebankingapi.Common.exception

open class BaseException(
    override val message: String ?
) : RuntimeException(message)
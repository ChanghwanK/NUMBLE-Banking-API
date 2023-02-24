package co.numble.bank.numblebankingapi.Common.exception

import co.numble.bank.numblebankingapi.Common.exception.enums.ErrorMessage

class DuplicatedEmailException (
    override val message: String = ErrorMessage.DUPLICATED_EMAIL.message
): BaseException(message)
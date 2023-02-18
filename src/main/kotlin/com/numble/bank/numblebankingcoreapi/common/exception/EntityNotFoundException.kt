package co.numble.bank.numblebankingapi.Common.exception

import co.numble.bank.numblebankingapi.Common.exception.enums.ErrorMessage

class EntityNotFoundException(
    override val message: String = ErrorMessage.ENTITY_NOT_FOUND.message
) : BaseException(message)
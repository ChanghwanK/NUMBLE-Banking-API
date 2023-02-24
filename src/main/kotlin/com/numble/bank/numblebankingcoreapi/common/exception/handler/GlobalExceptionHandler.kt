package co.numble.bank.numblebankingapi.Common.exception.handler

import co.numble.bank.numblebankingapi.Common.exception.CommonErrorResponse
import co.numble.bank.numblebankingapi.Common.exception.DuplicatedEmailException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = KotlinLogging.logger {}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedEmailException::class)
    fun handleDuplicatedEmailException(ex: DuplicatedEmailException): CommonErrorResponse {
        logger.warn { ex.message }
        return CommonErrorResponse.of(HttpStatus.BAD_REQUEST.value(), ex.message)
    }
}
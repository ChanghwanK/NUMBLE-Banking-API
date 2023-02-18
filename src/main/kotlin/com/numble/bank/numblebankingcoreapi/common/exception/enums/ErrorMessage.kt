package co.numble.bank.numblebankingapi.Common.exception.enums

enum class ErrorMessage(val message: String) {
    DUPLICATED_EMAIL("중복된 이메일이 존재합니다."),
    ENTITY_NOT_FOUND("등록된 정보가 존재하지 않습니다.")
}
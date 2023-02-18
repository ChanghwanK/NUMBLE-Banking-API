package co.numble.bank.numblebankingapi.Common.exception

class CommonErrorResponse(
    val code: Int,
    val message: String
) {
    companion object {
        fun of(code: Int, message: String): CommonErrorResponse {
            return CommonErrorResponse(code, message)
        }
    }
}
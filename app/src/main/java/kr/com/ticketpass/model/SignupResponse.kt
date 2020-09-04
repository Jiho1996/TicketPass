package kr.com.ticketpass.model

data class SignupResponse (
    val accessToken: String,
    val tokenType: String,
    val user: UserInfo
) {
    data class UserInfo(
        val id: String,
        val name: String,
        val email: String
    )
}
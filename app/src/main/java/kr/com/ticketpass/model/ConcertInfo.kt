package kr.com.ticketpass.model

data class ConcertInfo (
    val id: String,
    val name: String,
    val startTime: String,
    val endTime: String,
    val startDate: String,
    val spreadsheetLink: String,
    val topImageLink: String,
    val bottomImageLink: String,
    val host: SignupResponse.UserInfo
)
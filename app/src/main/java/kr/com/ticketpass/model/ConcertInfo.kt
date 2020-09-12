package kr.com.ticketpass.model

data class ConcertInfo (
    val id: String,
    val name: String,
    val startTime: String,
    val enterTime: String,
    val place: String,
    val spreadsheetId: String,
    val spreadsheetLink: String,
    val topImageLink: String,
    val bottomImageLink: String,
)
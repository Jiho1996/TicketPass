package kr.com.ticketpass.model

import java.net.URL

data class ConcertInfo (
    val id: String,
    val name: String,
    val startTime: String,
    val endTime: String,
    val place: String,
    val spreadsheetId: String,
    val spreadsheetLink: URL,
    val topImageLink: String,
    val bottomImageLink: String,
)
package kr.com.ticketpass.model

import java.net.URL

data class ConcertResponse (
    val concerts : List<ConcertInfo>
    ){
    data class ConcertInfo (
        val id: String,
        val name: String,
        val startTime: String,
        val enterTime: String,
        val place: String,
        val spreadsheetId: String,
        val spreadsheetLink: URL,
        val topImageLink: String,
        val bottomImageLink: String,
    )
}
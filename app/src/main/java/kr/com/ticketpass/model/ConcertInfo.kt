package kr.com.ticketpass.model

import java.io.Serializable

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
    var expanded: Boolean
): Serializable
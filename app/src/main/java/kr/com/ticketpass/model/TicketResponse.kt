package kr.com.ticketpass.model

import java.io.Serializable

data class TicketResponse (
    val unexpiredTickets: List<TicketInfo>,
    val expiredTickets: List<TicketInfo>,
    val nextTicket: TicketInfo
) {
    data class TicketInfo (
        val id: String,
        val seatClass: String,
        val isUsed: Boolean,
        val userName: String,
        val concert: ConcertInfo,
        val userPhoneNumber: String,
        var expanded: Boolean,
        var isExpired: Boolean
    ): Serializable
}
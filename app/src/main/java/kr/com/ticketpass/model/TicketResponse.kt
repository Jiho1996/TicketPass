package kr.com.ticketpass.model

import java.io.Serializable

data class TicketResponse (
    val unexpiredTickets: List<TicketInfo>,
    val expiredTickets: List<TicketInfo>,
    val nextTicket: TicketInfo
) {
    data class TicketInfo (
        val id: String = "",
        val seatClass: String = "",
        val isUsed: Boolean = true,
        val userName: String = "",
        val concert: ConcertInfo = ConcertInfo(),
        val userPhoneNumber: String = "",
        var expanded: Boolean = true,
        var isExpired: Boolean = true
    ): Serializable
}
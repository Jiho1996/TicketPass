package kr.com.ticketpass.model

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
        var expanded: Boolean,
        var isExpired: Boolean
    )
}
package kr.com.ticketpass.viewmodel

import androidx.lifecycle.ViewModel

class GuestMainViewModel: ViewModel() {
    fun ticketListSort(list: List<Any>) {
        //날짜 최신순으로 리스트 정렬
    }

    fun isUselessTicket(list: List<Any>) {
        //오늘보다 이전 날짜면 티켓 검은색 될 수 있게 처리
    }

    fun getTicketList() {
        //서버로부터 티켓 정보 가져옴
    }
}
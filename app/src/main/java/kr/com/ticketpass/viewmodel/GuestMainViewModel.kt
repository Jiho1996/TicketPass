package kr.com.ticketpass.viewmodel

import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.com.ticketpass.model.TicketResponse
import kr.com.ticketpass.network.requestApi
import kr.com.ticketpass.util.ConstValue
import kr.com.ticketpass.util.SharedPreferenceManager
import kr.com.ticketpass.util.SingleLiveEvent

class GuestMainViewModel : ViewModel() {
    lateinit var unexpiredList: List<TicketResponse.TicketInfo>
    lateinit var expiredList: List<TicketResponse.TicketInfo>
    lateinit var nextTicket: TicketResponse.TicketInfo
    val getTicketSuccess: SingleLiveEvent<Void> = SingleLiveEvent()

    fun ticketListSort(list: List<Any>) {
        //날짜 최신순으로 리스트 정렬
    }

    fun isUselessTicket(list: List<Any>) {
        //오늘보다 이전 날짜면 티켓 검은색 될 수 있게 처리
    }

    fun getTicketList() {
        requestApi.getTickets(
            "Bearer " + SharedPreferenceManager.getToken(),
            SharedPreferenceManager.getStringPref(ConstValue.CONST_USER_ID),
            3,
            3
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                unexpiredList = it.unexpiredTickets
                expiredList = it.expiredTickets
                nextTicket = it.nextTicket
                getTicketSuccess.call()
            }, {
                Logger.d(it.localizedMessage)
            })
    }
}
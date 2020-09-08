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
import java.text.SimpleDateFormat
import java.util.*

class GuestMainViewModel : ViewModel() {
    lateinit var unexpiredList: List<TicketResponse.TicketInfo>
    lateinit var expiredList: List<TicketResponse.TicketInfo>
    lateinit var nextTicket: TicketResponse.TicketInfo
    var allTicketList: MutableList<TicketResponse.TicketInfo> = mutableListOf()
    val getTicketSuccess: SingleLiveEvent<Void> = SingleLiveEvent()

    fun ticketListSort(list: List<TicketResponse.TicketInfo>) {
        //날짜 최신순으로 리스트 정렬
        val transFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        allTicketList.addAll(list)
        for (i in allTicketList) {
            print(i.concert.startTime)
        }

        Collections.sort(allTicketList, object : Comparator<TicketResponse.TicketInfo> {
            override fun compare(o1: TicketResponse.TicketInfo?, o2: TicketResponse.TicketInfo?): Int {
                val date1 = transFormat.parse(o1?.concert?.startTime)
                val date2 = transFormat.parse(o2?.concert?.startTime)

                if (date1 > date2) {
                    return -1
                } else if (date1 < date2) {
                    return 1
                } else {
                    return 0
                }
            }
        })

        for (i in allTicketList) {
            print(i.concert.startTime)
        }
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
                unexpiredList.map {
                    it.isExpired = false
                }
                ticketListSort(unexpiredList)

                expiredList = it.expiredTickets
                expiredList.map {
                    it.isExpired = true
                }
                ticketListSort(expiredList)

                nextTicket = it.nextTicket
                nextTicket.isExpired = false

                allTicketList.add(0, nextTicket)
                getTicketSuccess.call()
            }, {
                Logger.d(it.localizedMessage)
            })
    }
}
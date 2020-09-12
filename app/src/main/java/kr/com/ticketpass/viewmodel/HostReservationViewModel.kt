package kr.com.ticketpass.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.com.ticketpass.model.TicketResponse
import kr.com.ticketpass.network.requestApi
import kr.com.ticketpass.util.ConstValue
import kr.com.ticketpass.util.SharedPreferenceManager
import kr.com.ticketpass.util.SingleLiveEvent

class HostReservationViewModel: ViewModel() {
    val ticketQrSuccess: SingleLiveEvent<Void> = SingleLiveEvent()
    var ticketInfoQr: MutableLiveData<TicketResponse.TicketInfo> = MutableLiveData()

    fun useTicketQr(ticketData: String) {
        requestApi.postTickets(
            "Bearer " + SharedPreferenceManager.getToken(),
            SharedPreferenceManager.getStringPref(ConstValue.CONST_USER_ID),
            ticketData
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                ticketQrSuccess.call()
            }, {
                Logger.d(it.localizedMessage)
            })
    }

    fun getTicketInfo(userId: String, ticketId: String) {
        requestApi.getTicketQr(
            "Bearer " + SharedPreferenceManager.getToken(),
            ticketId,
            userId
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                ticketInfoQr.value = it
            }, {
                Logger.d(it.localizedMessage)
            })
    }
}
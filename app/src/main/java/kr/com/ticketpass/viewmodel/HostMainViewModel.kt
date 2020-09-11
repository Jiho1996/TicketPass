package kr.com.ticketpass.viewmodel

import android.annotation.SuppressLint
import android.app.TaskStackBuilder.create
import android.renderscript.ScriptIntrinsic3DLUT.create
import android.telecom.Call
import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger.d
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.com.ticketpass.model.ConcertInfo
import kr.com.ticketpass.model.NewConcertForm
import kr.com.ticketpass.model.TicketResponse
import kr.com.ticketpass.network.Api
import kr.com.ticketpass.network.requestApi
import kr.com.ticketpass.util.ConstValue
import kr.com.ticketpass.util.SharedPreferenceManager
import kr.com.ticketpass.util.SingleLiveEvent
import retrofit2.Callback
import java.net.URI.create
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.Collections.list
import java.util.logging.Logger
import kotlin.Comparator

class HostMainViewModel : ViewModel() {
    var name: String = ""
    var startTime: String = ""
    var enterTime: String = ""
    var place: String = ""
    val id: String = ""
    val spreadsheetId: String = ""
    //val spreadsheetLink: URL = "https://spreadsheet.link"
    val topImageLink: String = ""
    val bottomImageLink: String = ""
    lateinit var unexpiredList: List<TicketResponse.TicketInfo>
    lateinit var expiredList: List<TicketResponse.TicketInfo>
    lateinit var nextTicket: TicketResponse.TicketInfo
    var allTicketList: MutableList<TicketResponse.TicketInfo> = mutableListOf()
    val getTicketSuccess: SingleLiveEvent<Void> = SingleLiveEvent()
    var isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val createSuccess: SingleLiveEvent<Void> = SingleLiveEvent()
    val getConcertInfoSuccess: SingleLiveEvent<Void> = SingleLiveEvent()
    val postConcertSyncSuccess: SingleLiveEvent<Void> = SingleLiveEvent()
    val getConcertListSuccess : SingleLiveEvent<Void> = SingleLiveEvent()

    @SuppressLint("CheckResult")
    fun createConcert() {
        requestApi.postConcert(
            "Bearer " + SharedPreferenceManager.getToken(),
            NewConcertForm(name, startTime, enterTime, place)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                createSuccess.call()
            }, {
                com.orhanobut.logger.Logger.d(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    fun getConcertInfo(list: List<ConcertInfo>) {
        requestApi.getConcert(
            "Bearer " + SharedPreferenceManager.getToken(),
            SharedPreferenceManager.getStringPref(ConstValue.CONST_USER_ID)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getConcertInfoSuccess.call()
            }, {
                com.orhanobut.logger.Logger.d(it.localizedMessage)
            }

            )
    }
    @SuppressLint("CheckResult")
    fun postConcertSync() {
        requestApi.syncConcert(
            "Bearer " + SharedPreferenceManager.getToken(),
            SharedPreferenceManager.getStringPref(ConstValue.CONST_SPREADSHEET_ID) // spreadsheet 코드 바
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postConcertSyncSuccess.call()
            }, {
                com.orhanobut.logger.Logger.d(it.localizedMessage)
            }
            )
    }
    @SuppressLint("CheckResult")
    fun getConcertList(list: List<ConcertInfo>){
        requestApi.getUserConcert("Bearer "+ SharedPreferenceManager.getToken(),10,3,id
                )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getConcertListSuccess.call()
            },{
                com.orhanobut.logger.Logger.d(it.localizedMessage)
            })
    }
    fun ticketListSort(list: List<TicketResponse.TicketInfo>) {
        //날짜 최신순으로 리스트 정렬
        val transFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        allTicketList.addAll(list)
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
    }
    @SuppressLint("CheckResult")
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

                if (allTicketList.size != 0) {
                    isEmpty.value = true
                }

                getTicketSuccess.call()
            }, {
                com.orhanobut.logger.Logger.d(it.localizedMessage)
            })
    }
}


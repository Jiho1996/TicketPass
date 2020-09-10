package kr.com.ticketpass.viewmodel

import android.annotation.SuppressLint
import android.app.TaskStackBuilder.create
import android.renderscript.ScriptIntrinsic3DLUT.create
import android.telecom.Call
import androidx.lifecycle.ViewModel
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
import java.util.Collections.list
import java.util.logging.Logger

class HostMainViewModel : ViewModel() {
    var name: String = ""
    var startTime: String = ""
    var enterTime: String = ""
    var place: String = ""
    val id: String = ""
    val spreadsheetId: String = ""
   // val spreadsheetLink: URL = "https://spreadsheet.link"
    val topImageLink: String = ""
    val bottomImageLink: String = ""
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
            SharedPreferenceManager.getStringPref(ConstValue.CONST_SPREADSHEET_ID)
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
}

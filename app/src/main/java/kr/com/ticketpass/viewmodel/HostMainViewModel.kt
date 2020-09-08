package kr.com.ticketpass.viewmodel

import androidx.lifecycle.ViewModel
import kr.com.ticketpass.model.NewConcertForm
import kr.com.ticketpass.network.requestApi

class HostMainViewModel : ViewModel() {
    var name: String = ""
    var startTime: String = ""
    var enterTime: String = ""
    var place : String = ""

    fun createConcert(name : String, startTime : String, endTime : String, startDate : String) {
      //  requestApi.postConcert(NewConcertForm())

    }
}
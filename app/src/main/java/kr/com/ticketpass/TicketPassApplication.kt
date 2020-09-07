package kr.com.ticketpass

import android.app.Application
import android.content.Context

class TicketPassApplication: Application() {
    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
    }
}

package kr.com.ticketpass.host

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_guest_list.*
import kr.com.ticketpass.R
import kr.com.ticketpass.model.ConcertInfo
import kr.com.ticketpass.model.ConcertResponse
import kr.com.ticketpass.network.requestApi

class HostEditGuestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_guest_list)
        button.setOnClickListener {

     //       val intent = Intent(Intent.ACTION_VIEW, Uri.parse())
            startActivity(intent)
        }
    }
}

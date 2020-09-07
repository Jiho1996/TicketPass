package kr.com.ticketpass.host

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_host_about_event.*
import kr.com.ticketpass.R

class HostManageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_about_event)

        event_info_button.setOnClickListener {
            startActivity(Intent(this, HostEventManageActivity::class.java))
        }
        guest_list_button.setOnClickListener {
            startActivity(Intent(this, HostEditGuestActivity::class.java))
        }
        }
    override fun onBackPressed() {
        super.onBackPressed()
    }
    }

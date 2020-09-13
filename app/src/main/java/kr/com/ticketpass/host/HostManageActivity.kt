package kr.com.ticketpass.host

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_host_about_event.*
import kr.com.ticketpass.R
import kr.com.ticketpass.model.TicketResponse
import java.io.Serializable

class HostManageActivity : AppCompatActivity() {

  private lateinit var ticket : TicketResponse.TicketInfo

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_host_about_event)

            val tempIntent = intent.getSerializableExtra("ticket")
            if (tempIntent != null) {
                ticket = (tempIntent as TicketResponse.TicketInfo)
            }

            event_info_button.setOnClickListener {
                startActivity(Intent(this, HostEventManageActivity::class.java))
            }
            guest_list_button.setOnClickListener {
                val intent =Intent(this,HostEditGuestActivity::class.java)
                intent.putExtra("ticket",ticket as Serializable)
                startActivity(intent)
            }
        }
    override fun onBackPressed() {
        super.onBackPressed()
    }
    }

package kr.com.ticketpass.selectsigntype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_select_type.*
import kr.com.ticketpass.R
import kr.com.ticketpass.host.HostLoginActivity
import kr.com.ticketpass.guest.GuestLoginActivity


class SelectTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_type)

        att_login_button.setOnClickListener {
            startActivity(Intent(this, GuestLoginActivity::class.java))
        }
        host_login_button.setOnClickListener {
            startActivity(Intent(this, HostLoginActivity::class.java))
        }
    }
}
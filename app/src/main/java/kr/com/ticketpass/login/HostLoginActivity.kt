package kr.com.ticketpass.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login_host.*
import kr.com.ticketpass.R
import kr.com.ticketpass.signup.HostSignUpEmailActivity


class HostLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_host)

        host_login_signup_text_host.setOnClickListener {
            startActivity(Intent(this, HostSignUpEmailActivity::class.java))
        }

    }
}
package kr.com.ticketpass.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login_att.*
import kotlinx.android.synthetic.main.activity_login_host.*
import kr.com.ticketpass.R
import kr.com.ticketpass.signup.HostSignUpActivity


class HostLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_host)

        login_signup_text_host.setOnClickListener {
            val intent = Intent(this, HostSignUpActivity::class.java)
            startActivity(intent)
        }

    }
}
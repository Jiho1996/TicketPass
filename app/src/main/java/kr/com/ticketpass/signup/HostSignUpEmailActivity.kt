package kr.com.ticketpass.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_host_sign_up_email.*
import kr.com.ticketpass.R


class HostSignUpEmailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_host_sign_up_email)

        host_next_button.setOnClickListener {
            startActivity(Intent(this, HostSignUpPwActivity::class.java))
        }
    }
}
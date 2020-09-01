package kr.com.ticketpass.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_host_sign_up_01.*
import kotlinx.android.synthetic.main.activity_host_sign_up_02.*
import kotlinx.android.synthetic.main.activity_select_type.*
import kr.com.ticketpass.R


class HostSignUpActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_sign_up_01)

        host_next_button.setOnClickListener {
           setContentView(R.layout.activity_host_sign_up_02)
        }
    }
}
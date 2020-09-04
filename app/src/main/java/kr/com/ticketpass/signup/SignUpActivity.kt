package kr.com.ticketpass.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login_host.*
import kotlinx.android.synthetic.main.fragment_host_sign_up_email.*
import kr.com.ticketpass.R

class SignUpActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     /*   host_next_button.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.layout.fragment_att_sign_up_email, HostSignUpPwFragment())
                .commit()
        }
*/
    }
}
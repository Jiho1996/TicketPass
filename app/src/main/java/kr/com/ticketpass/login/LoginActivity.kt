package kr.com.ticketpass.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login_att.*
import kr.com.ticketpass.R
import kr.com.ticketpass.signup.SignUpEmailActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_att)

        login_signup_text_att.setOnClickListener {
            startActivity(Intent(this, SignUpEmailActivity::class.java))
        }
        
        }

    }

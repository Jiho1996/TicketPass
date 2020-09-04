package kr.com.ticketpass.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_att_sign_up_email.*
import kr.com.ticketpass.R

class SignUpEmailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_att_sign_up_email)

        att_next_button.setOnClickListener {
            startActivity(Intent(this, SignUpPwActivity::class.java))
        }
    }
}
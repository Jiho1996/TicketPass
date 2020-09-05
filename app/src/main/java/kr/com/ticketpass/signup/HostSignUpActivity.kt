package kr.com.ticketpass.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login_host.*
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityLoginHostBinding
import kr.com.ticketpass.viewmodel.LoginViewModel


class HostSignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        host_login_signup_text_host.setOnClickListener {
            changeFragment(HostSignUpEmailFragment()) // (1)
        }
    }

    fun changeFragment(f: HostSignUpEmailFragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction() // (2) 프레그먼트 관리자를 통한 제어
        ft.replace(R.id.host_login_signup_text_host, f) // (3) 프레그먼트의 변경
        ft.addToBackStack(null) // (4) 백스택에 넣기
        ft.commit() // (5) 최종 프레그먼트의 적용
    }
}
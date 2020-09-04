package kr.com.ticketpass.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login_host.*
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityLoginHostBinding
import kr.com.ticketpass.signup.HostSignUpEmailFragment
import kr.com.ticketpass.viewmodel.LoginViewModel


class HostLoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_host)
        initDataBinding()

      /*  host_login_signup_text_host.setOnClickListener {
            changeFragment(HostSignUpEmailFragment()) // (1)
        }
*/

    }

    private fun initDataBinding() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_host)
        binding.viewModel = viewModel
    }
  /*  fun changeFragment(f: HostSignUpEmailFragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction() // (2) 프레그먼트 관리자를 통한 제어
        ft.replace(R.id.host_login_signup_text_host, f) // (3) 프레그먼트의 변경
        ft.addToBackStack(null) // (4) 백스택에 넣기
        ft.commit() // (5) 최종 프레그먼트의 적용
    }
    */


}
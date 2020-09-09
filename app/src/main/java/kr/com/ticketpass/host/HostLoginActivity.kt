package kr.com.ticketpass.host

import kr.com.ticketpass.databinding.ActivityLoginHostBinding
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.util.toastUtil
import kr.com.ticketpass.viewmodel.LoginViewModel

class HostLoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    private lateinit var binding: ActivityLoginHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_host)
        binding.viewModel = viewModel

        setLivedataObserver()

        binding.hostLoginSignup.setOnClickListener {
            startActivity(Intent(this, HostSignUpActivity::class.java))
        }
    }

    fun onClickLogin(view: View) {
        if (binding.hostLoginEmail.text.isNullOrEmpty()) {
            this.toastUtil("이메일을 입력해주십시오")
        } else if (binding.hostLoginPasswordEditText.text.isNullOrEmpty()) {
            this.toastUtil("비밀번호를 입력해주십시오")
        } else {
            /*viewModel.doLogin(
                    binding.guestLoginEmail.text.toString(),
                    binding.guestLoginPassword.text.toString(),
                    "PARTICIPANT"
                )*/
            viewModel.doLogin(
                "skfk0135@gmail.com",
                "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08",
                "HOST"
            )
        }
    }

    private fun setLivedataObserver() {
        viewModel.loginSuccess.observe(this, Observer {
            startActivity(Intent(this, HostMainActivity::class.java))
        })
    }
}

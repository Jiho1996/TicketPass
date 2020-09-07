package kr.com.ticketpass.guest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_login_guest.*
import kotlinx.android.synthetic.main.activity_login_host.*
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityLoginGuestBinding
import kr.com.ticketpass.databinding.ActivityLoginHostBinding
import kr.com.ticketpass.host.GuestSignupActivity
import kr.com.ticketpass.host.HostSignUpActivity
import kr.com.ticketpass.viewmodel.LoginViewModel

class GuestLoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    private lateinit var binding: ActivityLoginGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_guest)
        binding.viewModel = viewModel

        setLivedataObserver()

        binding.guestLoginSignup.setOnClickListener {
            startActivity(Intent(this, GuestSignupActivity::class.java))
        }

        binding.guestLoginBtn.setOnClickListener {
            Logger.d("hello")
            /*viewModel.doLogin(
                binding.guestLoginEmail.text.toString(),
                binding.guestLoginPassword.text.toString(),
                "PARTICIPANT"
            )*/
            viewModel.doLogin(
                "skfk0135@gmail.com",
                "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"
                ,
                "PARTICIPANT"
            )
        }
    }

    private fun setLivedataObserver() {
        viewModel.loginSuccess.observe(this, Observer {
            startActivity(Intent(this, GuestMainActivity::class.java))
        })
    }
}
package kr.com.ticketpass.host

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login_host.*
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityLoginHostBinding
import kr.com.ticketpass.viewmodel.LoginViewModel


class HostLoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    private val binding: ActivityLoginHostBinding by lazy {
        ActivityLoginHostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_host)
        binding.viewModel = viewModel

        host_login_signup_text_host.setOnClickListener {
            startActivity(Intent(this, HostSignUpActivity::class.java))
        }
    }

}
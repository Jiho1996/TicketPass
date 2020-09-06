package kr.com.ticketpass.host

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityLoginHostBinding
import kr.com.ticketpass.viewmodel.LoginViewModel


class HostLoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private val binding: ActivityLoginHostBinding by lazy {
        ActivityLoginHostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_host)
        initDataBinding()

        binding.hostLoginSignupTextHost.setOnClickListener {
            startActivity(Intent(this, HostSignUpActivity::class.java))
        }
    }

    private fun initDataBinding() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
    }


}
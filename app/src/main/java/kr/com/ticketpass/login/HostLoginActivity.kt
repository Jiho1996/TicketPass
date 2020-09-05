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
    }

    private fun initDataBinding() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_host)
        binding.viewModel = viewModel
    }


}
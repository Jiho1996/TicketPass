package kr.com.ticketpass.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityHostSignupBinding
import kr.com.ticketpass.viewmodel.SignupViewModel


class HostSignUpActivity : AppCompatActivity() {
    private val viewModel: SignupViewModel by lazy {
        ViewModelProvider(this).get(SignupViewModel::class.java)
    }
    private val binding: ActivityHostSignupBinding by lazy {
        ActivityHostSignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeFragment()
    }

    private fun changeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.host_login_signup_text_host, HostSignUpEmailFragment())
            .addToBackStack(null)
            .commit()
    }
}
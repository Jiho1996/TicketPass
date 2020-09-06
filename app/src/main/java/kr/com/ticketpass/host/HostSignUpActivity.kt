package kr.com.ticketpass.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.viewmodel.SignupViewModel


class HostSignUpActivity : AppCompatActivity() {
    private val viewModel: SignupViewModel by lazy {
        ViewModelProvider(this).get(SignupViewModel::class.java)
    }
    private val hostSignUpEmailFragment = HostSignUpEmailFragment.newInstance()
    private val hostSignUpPwFragment = HostSignUpPwFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_sign_up)
        navigateEmailFragment()
    }

    private fun navigateEmailFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.host_sign_up_container, hostSignUpEmailFragment)
            .addToBackStack(null)
            .commit()
    }

    fun navigatePwFragment() {
        supportFragmentManager.beginTransaction().add(R.id.host_sign_up_container, hostSignUpPwFragment).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.beginTransaction().remove(hostSignUpPwFragment).commit()
        navigateEmailFragment()
    }
}
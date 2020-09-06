package kr.com.ticketpass.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivitySignUpBinding
import kr.com.ticketpass.guest.SignUpEmailFragment
import kr.com.ticketpass.guest.SignUpPwFragment
import kr.com.ticketpass.viewmodel.SignupViewModel

class SignupActivity : AppCompatActivity() {
    private val viewModel: SignupViewModel by lazy {
        ViewModelProvider(this).get(SignupViewModel::class.java)
    }

    private val signUpEmailFragment = SignUpEmailFragment.newInstance()
    private val signUpPwFragment = SignUpPwFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        navigateEmailFragment()
    }

    private fun navigateEmailFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.sign_up_container, signUpEmailFragment)
            .addToBackStack(null)
            .commit()
    }

    fun navigatePwFragment() {
        supportFragmentManager.beginTransaction().add(R.id.sign_up_container, signUpPwFragment).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.beginTransaction().remove(signUpPwFragment).commit()
        navigateEmailFragment()
    }
}
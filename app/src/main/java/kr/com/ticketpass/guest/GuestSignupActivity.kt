package kr.com.ticketpass.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityDefaultSignupBinding
import kr.com.ticketpass.guest.GuestSignUpEmailFragment
import kr.com.ticketpass.guest.GuestSignUpPwFragment
import kr.com.ticketpass.viewmodel.SignupViewModel

class GuestSignupActivity : AppCompatActivity() {
    private val viewModel: SignupViewModel by lazy {
        ViewModelProvider(this@GuestSignupActivity).get(SignupViewModel::class.java)
    }

    private lateinit var binding: ActivityDefaultSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_default_signup)

        changeFragment("email")
    }

    fun changeFragment(type: String) {
        when (type) {
            "email" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.sign_up_container, GuestSignUpEmailFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "password" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.sign_up_container, GuestSignUpPwFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
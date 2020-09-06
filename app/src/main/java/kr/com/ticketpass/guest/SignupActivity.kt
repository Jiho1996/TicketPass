package kr.com.ticketpass.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivitySignupBinding
import kr.com.ticketpass.guest.SignUpEmailFragment
import kr.com.ticketpass.guest.SignUpPwFragment
import kr.com.ticketpass.viewmodel.SignupViewModel

class SignupActivity : AppCompatActivity() {
    private val viewModel: SignupViewModel by lazy {
        ViewModelProvider(this@SignupActivity).get(SignupViewModel::class.java)
    }

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        changeFragment("email")
    }

    fun changeFragment(type: String) {
        when (type) {
            "email" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.sign_up_container, SignUpEmailFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "password" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.sign_up_container, SignUpPwFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
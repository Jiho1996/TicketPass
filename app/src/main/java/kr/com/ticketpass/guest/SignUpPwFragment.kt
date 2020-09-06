package kr.com.ticketpass.guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.databinding.FragmentGuestSignUpPwBinding
import kr.com.ticketpass.viewmodel.SignupViewModel
import kr.com.ticketpass.util.isValidatePasswordAndRePassword
import kr.com.ticketpass.util.toastUtil

class SignUpPwFragment : Fragment() {
    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentGuestSignUpPwBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding = FragmentGuestSignUpPwBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        binding.guestSignupPasswordBtn.setOnClickListener {
            if (isValidatePasswordAndRePassword(
                binding.guestSignupPassword.text.toString(),
                binding.guestSignupPasswordVerify.text.toString())) {
                viewModel.password = binding.guestSignupPassword.text.toString()
                viewModel.doSignup()
            } else {
                activity?.toastUtil("패스워드가 적절하지 않거나 일치하지 않습니다.")
            }
        }

        return binding.root
    }
}

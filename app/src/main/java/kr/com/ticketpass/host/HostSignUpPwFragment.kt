package kr.com.ticketpass.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.FragmentGuestSignUpPwBinding
import kr.com.ticketpass.databinding.FragmentHostSignUpPwBinding
import kr.com.ticketpass.util.isValidatePasswordAndRePassword
import kr.com.ticketpass.util.toastUtil
import kr.com.ticketpass.viewmodel.SignupViewModel

class HostSignUpPwFragment : Fragment() {
    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentHostSignUpPwBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding = FragmentHostSignUpPwBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        binding.hostSignupSubmitBtn.setOnClickListener {
            if (isValidatePasswordAndRePassword(
                    binding.hostSignupPassword.text.toString(),
                    binding.hostSignupPasswordVerify.text.toString())
            ) {
                viewModel.password = binding.hostSignupPassword.text.toString()
                viewModel.doSignup()
            } else {
                activity?.toastUtil("패스워드가 적절하지 않거나 일치하지 않습니다.")
            }
        }

        return binding.root

    }
}

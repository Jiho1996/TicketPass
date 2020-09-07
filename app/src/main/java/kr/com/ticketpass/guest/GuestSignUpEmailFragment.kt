package kr.com.ticketpass.guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.databinding.FragmentGuestSignUpEmailBinding
import kr.com.ticketpass.host.GuestSignupActivity
import kr.com.ticketpass.util.toastUtil
import kr.com.ticketpass.viewmodel.SignupViewModel

class GuestSignUpEmailFragment : Fragment() {
    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentGuestSignUpEmailBinding

    companion object {
        fun newInstance(): GuestSignUpEmailFragment {
            return GuestSignUpEmailFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding = FragmentGuestSignUpEmailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        binding.guestSignupEmailCodeRequest.setOnClickListener {
            if (binding.guestSignupEmail.text.toString().isNotBlank()) {
                viewModel.sendEmailCode(binding.guestSignupEmail.text.toString())
            } else {
                activity?.toastUtil("이메일을 입력해주십시오")
            }
        }

        binding.guestSignupNextBtn.setOnClickListener {
            if (binding.guestSignupEmailCode.text.toString().isNotEmpty()) {
                viewModel.email = binding.guestSignupEmail.text.toString()
                viewModel.code = binding.guestSignupEmailCode.text.toString()
                (activity as GuestSignupActivity).changeFragment("password")
            } else {
                activity?.toastUtil("인증번호를 입력해주십시오.")
            }
        }

        return binding.root
    }
}
package kr.com.ticketpass.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kr.com.ticketpass.databinding.FragmentHostSignUpEmailBinding
import kr.com.ticketpass.util.toastUtil
import kr.com.ticketpass.viewmodel.SignupViewModel


class HostSignUpEmailFragment : Fragment() {
    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentHostSignUpEmailBinding

    companion object {
        fun newInstance(): HostSignUpEmailFragment {
            return HostSignUpEmailFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(activity as ViewModelStoreOwner).get(SignupViewModel::class.java)
        binding = FragmentHostSignUpEmailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        binding.hostSignupEmailRequest.setOnClickListener {
            if (binding.hostSignupEmail.text.toString().isNotBlank()) {
                viewModel.sendEmailCode(binding.hostSignupEmail.text.toString())
            } else {
                activity?.toastUtil("이메일을 입력해주십시오")
            }
        }

        binding.hostSignupNextBtn.setOnClickListener {
            if (binding.hostSignupEmailCode.text.toString().isNotEmpty()) {
                viewModel.email = binding.hostSignupEmail.text.toString()
                viewModel.code = binding.hostSignupEmailCode.text.toString()
                (activity as GuestSignupActivity).changeFragment("password")
            } else {
                activity?.toastUtil("인증번호를 입력해주십시오.")
            }
        }

        return binding.root
    }
}
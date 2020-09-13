package kr.com.ticketpass.host.login

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kr.com.ticketpass.databinding.FragmentHostSignUpEmailBinding
import kr.com.ticketpass.guest.login.GuestSignupActivity
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
            binding.hostSignupEmailRequest.isEnabled = false
            Handler().postDelayed(object: Runnable {
                override fun run() {
                    binding.hostSignupEmailRequest.isEnabled = true
                }
            }, 5000)
        }

        binding.hostSignupNextBtn.setOnClickListener {
            if (binding.hostSignupEmailCode.text.toString().isNotEmpty()) {
                viewModel.email = binding.hostSignupEmail.text.toString()
                viewModel.code = binding.hostSignupEmailCode.text.toString()
                (activity as HostSignUpActivity).changeFragment("password")
            } else {
                activity?.toastUtil("인증번호를 입력해주십시오.")
            }
        }

        viewModel.emailCodeSuccess.observe(viewLifecycleOwner, Observer {
            activity?.toastUtil("인증코드가 메일로 전송되었습니다")
        })

        viewModel.emailError.observe(viewLifecycleOwner, Observer {
            activity?.toastUtil("이메일 형식이 아니거나 이미 코드를 발송했습니다")
        })

        return binding.root
    }
}
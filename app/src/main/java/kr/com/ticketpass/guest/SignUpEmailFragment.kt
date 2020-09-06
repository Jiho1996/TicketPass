package kr.com.ticketpass.guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.databinding.FragmentGuestSignUpEmailBinding
import kr.com.ticketpass.host.HostSignUpEmailFragment
import kr.com.ticketpass.host.SignupActivity
import kr.com.ticketpass.viewmodel.SignupViewModel

class SignUpEmailFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentGuestSignUpEmailBinding

    companion object {
        fun newInstance(): SignUpEmailFragment {
            return SignUpEmailFragment()
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.guestNextButton.setOnClickListener {
            (activity as SignupActivity).navigatePwFragment()
        }
    }
}
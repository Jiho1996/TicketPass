package kr.com.ticketpass.guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.databinding.FragmentAttSignUpEmailBinding
import kr.com.ticketpass.host.HostSignUpEmailFragment
import kr.com.ticketpass.viewmodel.SignupViewModel

class SignUpEmailFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentAttSignUpEmailBinding

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
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding = FragmentAttSignUpEmailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }


}
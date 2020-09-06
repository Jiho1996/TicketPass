package kr.com.ticketpass.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.FragmentHostSignUpEmailBinding
import kr.com.ticketpass.viewmodel.SignupViewModel


class HostSignUpEmailFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
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
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding = FragmentHostSignUpEmailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hostNextButton.setOnClickListener {
            (activity as HostSignUpActivity).navigatePwFragment()
        }

    }
}
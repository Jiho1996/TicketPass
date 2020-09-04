package kr.com.ticketpass.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_host_sign_up_email.*
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


}
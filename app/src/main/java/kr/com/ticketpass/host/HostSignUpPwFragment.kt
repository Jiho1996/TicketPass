package kr.com.ticketpass.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.com.ticketpass.R

class HostSignUpPwFragment : Fragment() {
    companion object{
        fun newInstance() : HostSignUpPwFragment {
            return HostSignUpPwFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_host_sign_up_pw, container, false)

    }
}

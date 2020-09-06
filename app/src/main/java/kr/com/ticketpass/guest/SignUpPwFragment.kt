package kr.com.ticketpass.guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kr.com.ticketpass.R

class SignUpPwFragment : Fragment() {

    companion object{
        fun newInstance() : SignUpPwFragment {
            return SignUpPwFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_guest_sign_up_pw, container, false)

    }
}

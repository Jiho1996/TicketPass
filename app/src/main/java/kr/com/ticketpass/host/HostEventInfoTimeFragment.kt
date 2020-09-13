package kr.com.ticketpass.host

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_host_event_info_time.*
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.FragmentHostEventInfoTimeBinding
import kr.com.ticketpass.util.toastUtil
import kr.com.ticketpass.viewmodel.HostMainViewModel
import java.text.SimpleDateFormat
import java.util.*

class HostEventInfoTimeFragment : Fragment() {
    private lateinit var viewModel: HostMainViewModel
    private lateinit var binding: FragmentHostEventInfoTimeBinding

    companion object {
        fun newInstance(): HostEventInfoTimeFragment {
            return HostEventInfoTimeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.viewModelStore?.let {
            viewModel = ViewModelProvider(
                it,
                ViewModelProvider.NewInstanceFactory()
            ).get(HostMainViewModel::class.java)
        }
        binding = FragmentHostEventInfoTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eventInfoRegisterButton.setOnClickListener {
            if (binding.entranceTimeEdittext.text.toString().isBlank()) {
                activity?.toastUtil("입장 시작 시각을 입력해주십시오.")
            } else if (binding.eventInfoInputTime.text.toString().isBlank()) {
                activity?.toastUtil("공연 시작 시각을 입력해주십시오.")
            } else {
                viewModel.startTime = binding.entranceTimeEdittext.text.toString()
                viewModel.enterTime = binding.eventInfoInputTime.text.toString()
                viewModel.createConcert()
                val intent = Intent(context, HostMainActivity::class.java)
                activity?.startActivity(intent)
                activity?.finishAffinity()
            }
        }
    }
}






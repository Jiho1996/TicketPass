package kr.com.ticketpass.host

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentHostEventInfoTimeBinding

    var myDatePicker =
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

    companion object {
        fun newInstance(): HostEventInfoTimeFragment {
            return HostEventInfoTimeFragment()
        }
    }

    val myCalendar: Calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //  viewModel = ViewModelProvider(this).get(EventInfoViewModel::class.java)
        binding = FragmentHostEventInfoTimeBinding.inflate(inflater, container, false)
        //  binding.viewModel = viewModel
        return inflater.inflate(R.layout.fragment_host_event_info_time, container, false)

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
            }
        }

        entrance_time_edittext.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
            val minute = mcurrentTime[Calendar.MINUTE]
            val mTimePicker: TimePickerDialog

            context?.let { it ->
                DatePickerDialog(
                    it,
                    myDatePicker,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }


        private fun updateLabel() {
            val myFormat = "yyyy/MM/dd" // 출력형식   2018/11/28
            val sdf = SimpleDateFormat(myFormat, Locale.KOREA)
            val et_date = binding.entranceTimeEdittext
            et_date.setText(sdf.format(myCalendar.time))
        }
    }






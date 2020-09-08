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
import java.text.SimpleDateFormat
import java.util.*


class HostEventInfoTimeFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentHostEventInfoTimeBinding

    var myDatePicker =
        OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
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
                (activity as HostEventManageActivity).navigatePwFragment()
            }
        }

        entrance_time_edittext.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
            val minute = mcurrentTime[Calendar.MINUTE]
            val mTimePicker: TimePickerDialog

            mTimePicker = TimePickerDialog(
                getContext(),
                TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    var selectedHour = selectedHour
                    var state = "AM"
                    // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력
                    if (selectedHour > 12) {
                        selectedHour -= 12
                        state = "PM"
                    }
                    // EditText에 출력할 형식 지정
                    binding.entranceTimeEdittext.setText(state + " " + selectedHour + ":" + selectedMinute)
                },
                hour,
                minute,
                false
            ) // true의 경우 24시간 형식의 TimePicker 출현
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()

            getContext()?.let { it1 ->
                DatePickerDialog(
                    it1,
                    myDatePicker,
                    myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]
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





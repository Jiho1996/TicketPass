package kr.com.ticketpass.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.databinding.FragmentHostEventInfoEventBinding
import kr.com.ticketpass.util.toastUtil
import kr.com.ticketpass.viewmodel.HostMainViewModel

class HostEventInfoEventFragment : Fragment() {
    private lateinit var viewModel: HostMainViewModel
    private lateinit var binding: FragmentHostEventInfoEventBinding

    companion object {
        fun newInstance(): HostEventInfoEventFragment {
            return HostEventInfoEventFragment()
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
        binding = FragmentHostEventInfoEventBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eventInfoNextButton.setOnClickListener {
            if (binding.eventTitleEdittext.text.toString().isBlank()) {
                activity?.toastUtil("행사 이름을 입력해주십시오.")
            } else if (binding.eventInfoInputPlace.text.toString().isBlank()) {
                activity?.toastUtil("행사 장소를 입력해주십시오.")
            } else {
                viewModel.name = binding.eventTitleEdittext.text.toString()
                viewModel.place = binding.eventInfoInputPlace.text.toString()
                (activity as HostEventManageActivity).navigatePwFragment()
            }
        }
    }
}

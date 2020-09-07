package kr.com.ticketpass.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.FragmentHostEventInfoTimeBinding

class HostEventInfoTimeFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
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
        //  viewModel = ViewModelProvider(this).get(EventInfoViewModel::class.java)
        binding = FragmentHostEventInfoTimeBinding.inflate(inflater, container, false)
        //  binding.viewModel = viewModel
        return inflater.inflate(R.layout.fragment_host_event_info_time, container, false)

    }


    }

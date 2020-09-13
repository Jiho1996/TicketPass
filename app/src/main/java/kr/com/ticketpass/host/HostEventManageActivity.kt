package kr.com.ticketpass.host

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_host_event_info.*
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityHostEventInfoBinding
import kr.com.ticketpass.viewmodel.HostMainViewModel


class HostEventManageActivity : AppCompatActivity() {
    private val hostEventInfoEventFragment = HostEventInfoEventFragment.newInstance()
    public val hostEventInfoTimeFragment = HostEventInfoTimeFragment.newInstance()
    private lateinit var viewModel: HostMainViewModel
    private lateinit var binding: ActivityHostEventInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_event_info)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(HostMainViewModel::class.java)

        navigateEventFragment()

        next_text_view.setOnClickListener {
            startActivity(Intent(this, HostManageActivity::class.java))
        }
    }


    private fun navigateEventFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.event_info_container, hostEventInfoEventFragment)
            .addToBackStack(null)
            .commit()
    }

    fun navigatePwFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.event_info_container, hostEventInfoTimeFragment).addToBackStack(null).commit()
    }
}

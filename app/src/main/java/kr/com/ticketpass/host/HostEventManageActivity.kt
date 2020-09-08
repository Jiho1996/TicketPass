package kr.com.ticketpass.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.com.ticketpass.R


class HostEventManageActivity : AppCompatActivity() {
    private val hostEventInfoEventFragment = HostEventInfoEventFragment.newInstance()
    private val hostEventInfoTimeFragment = HostEventInfoTimeFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_event_info)

        navigateEventFragment()
    }

    private fun navigateEventFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.event_info_container, hostEventInfoEventFragment)
            .addToBackStack(null)
            .commit()
    }

    fun navigatePwFragment() {
        supportFragmentManager.beginTransaction().add(R.id.event_info_container, hostEventInfoTimeFragment).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.beginTransaction().remove(hostEventInfoTimeFragment).commit()
        navigateEventFragment()
    }
}
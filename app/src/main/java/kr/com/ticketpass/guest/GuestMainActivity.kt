package kr.com.ticketpass.guest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityGuestMainBinding
import kr.com.ticketpass.viewmodel.GuestMainViewModel

class GuestMainActivity: AppCompatActivity() {
    private val viewModel: GuestMainViewModel by lazy {
        ViewModelProvider(this).get(GuestMainViewModel::class.java)
    }

    private lateinit var binding: ActivityGuestMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guest_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.guestMainList.run {
            layoutManager = LinearLayoutManager(this@GuestMainActivity, RecyclerView.VERTICAL, false)
            adapter = GuestMainAdapter(mutableListOf(), binding.guestMainList)
        }
    }
}
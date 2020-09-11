package kr.com.ticketpass.guest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
    private lateinit var adapter: GuestMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guest_main)
        binding.viewModel = viewModel

        initRecyclerView()
        setLivedataObserver()
        viewModel.getTicketList()
    }

    private fun initRecyclerView() {
        val random = Math.random()
        val type = ((random*10) % 4).toInt()
        adapter = GuestMainAdapter(this, mutableListOf(), binding.guestMainList, type)
        binding.guestMainList.run {
            layoutManager = LinearLayoutManager(this@GuestMainActivity, RecyclerView.VERTICAL, false)
            adapter = this@GuestMainActivity.adapter
        }
    }

    private fun setLivedataObserver() {
        viewModel.getTicketSuccess.observe(this, Observer {
            adapter.addList(viewModel.allTicketList)
        })
    }
}
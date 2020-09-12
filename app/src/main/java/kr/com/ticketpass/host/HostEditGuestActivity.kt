package kr.com.ticketpass.host

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_guest_list.*
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityEditGuestListBinding
import kr.com.ticketpass.model.TicketResponse
import kr.com.ticketpass.viewmodel.HostMainViewModel

class HostEditGuestActivity : AppCompatActivity() {
    private val viewModel: HostMainViewModel by lazy {
        ViewModelProvider(this).get(HostMainViewModel::class.java)
    }
    private lateinit var binding: ActivityEditGuestListBinding
    val ticket = intent.getSerializableExtra("ticket") as TicketResponse.TicketInfo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_guest_list)


        insert_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ticket.concert.spreadsheetLink))
            startActivity(intent)
        }

        insert_save_button.setOnClickListener {
            viewModel.postConcertSync()
            startActivity(Intent(this, HostManageActivity::class.java))
        }

        viewModel.setString(ticket.concert.spreadsheetId)
    }
}



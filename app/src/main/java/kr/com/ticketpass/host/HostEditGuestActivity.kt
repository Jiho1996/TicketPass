package kr.com.ticketpass.host

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_guest_list.*
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityEditGuestListBinding
import kr.com.ticketpass.model.ConcertInfo
import kr.com.ticketpass.model.TicketResponse
import kr.com.ticketpass.util.toastUtil
import kr.com.ticketpass.viewmodel.HostMainViewModel

class HostEditGuestActivity : AppCompatActivity() {

    private val viewModel: HostMainViewModel by lazy {
        ViewModelProvider(this).get(HostMainViewModel::class.java)
    }
    private lateinit var binding: ActivityEditGuestListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_guest_list)
        val ticket = intent.getSerializableExtra("ticket") as ConcertInfo

        insert_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ticket.spreadsheetLink))
            startActivity(intent)
        }

        insert_save_button.setOnClickListener {
            viewModel.postConcertSync()
        }

        viewModel.postConcertSyncSuccess.observe(this, Observer {
            this.toastUtil("스프레드시트를 갱신했습니다")
            finish()
        })

        viewModel.postConcertSyncFailed.observe(this, Observer {
            this.toastUtil("스프레드시트 갱신에 실패했습니다")
        })

        viewModel.setString(ticket.spreadsheetId)
    }
}



package kr.com.ticketpass.host

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DecoratedBarcodeView.TorchListener
import kotlinx.android.synthetic.main.view_qrcode.*
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityManageEntranceBinding
import kr.com.ticketpass.util.toastUtil
import kr.com.ticketpass.viewmodel.HostReservationViewModel

class HostReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManageEntranceBinding
    private var userId = ""

    private val viewModel: HostReservationViewModel by lazy {
        ViewModelProvider(this).get(HostReservationViewModel::class.java)
    }
    private var qrData = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_entrance)
        binding.viewModel = viewModel

        binding.layoutCamera.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setBeepEnabled(false) //바코드 인식시 소리
            intentIntegrator.initiateScan()
        }

        binding.reserveSubmit.setOnClickListener {
            if (binding.qrName.text.isNotEmpty() && binding.qrSeat.text.isNotEmpty()) {
                viewModel.useTicketQr(userId, qrData)
            }
        }

        viewModel.ticketInfoQr.observe(this, Observer {
            binding.qrName.text = it.userName
            binding.qrSeat.text = it.seatClass
            binding.phoneNumber.text = it.userPhoneNumber
        })

        viewModel.ticketQrSuccess.observe(this, Observer {
            this.toastUtil("티켓 입장을 성공했습니다")
            finish()
        })

        viewModel.ticketQrFailed.observe(this, Observer {
            this.toastUtil("이미 입장한 관람객입니다")
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                this.toastUtil("티켓을 인식했습니다")
                val string = result.contents.split(" ")
                qrData = string[1] + " " + string[0]
                userId = string[0]
                val ticketId = string[1]
                viewModel.getTicketInfo(userId, ticketId)
        } else {
                this.toastUtil("error!")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

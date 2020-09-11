package kr.com.ticketpass.host

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

class HostReservationActivity : AppCompatActivity(), TorchListener {
    private lateinit var manager: CaptureManager
    private var isFlashOn = false // 플래시가 켜져 있는지
    private lateinit var btFlash: Button
    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var binding: ActivityManageEntranceBinding
    private val viewModel: HostReservationViewModel by lazy {
        ViewModelProvider(this).get(HostReservationViewModel::class.java)
    }
    private var qrData = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_entrance)
        barcodeView = findViewById(R.id.db_qr)

        manager = CaptureManager(this, barcodeView)
        manager.initializeFromIntent(intent, savedInstanceState)
        manager.decode()

        btFlash = findViewById(R.id.bt_flash)

        val intentIntegrator = IntentIntegrator(this)
        intentIntegrator.setBeepEnabled(false) //바코드 인식시 소리
        //intentIntegrator.captureActivity = HostReservationActivity::class.java
        intentIntegrator.initiateScan()

        bt_flash.setOnClickListener(View.OnClickListener {
            if (isFlashOn) {
                barcodeView.setTorchOff()
            } else {
                barcodeView.setTorchOn()
            }
        })

        binding.reserveReScan.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setBeepEnabled(false) //바코드 인식시 소리
            intentIntegrator.captureActivity = HostReservationActivity::class.java
            intentIntegrator.initiateScan()
        }

        binding.reserveSubmit.setOnClickListener {
            if (binding.qrName.text.isNotEmpty() && binding.qrSeat.text.isNotEmpty()) {
                viewModel.useTicketQr(qrData)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                this.toastUtil("contents: " + result.contents)
                val string = result.contents.split(" ")
                binding.qrName.text = string[0]
                binding.qrSeat.text = string[2]
                qrData = string[0] + " " + string[1]
            } else {
                this.toastUtil("error!")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onTorchOn() {
        btFlash.text = "플래시끄기"
        isFlashOn = true
    }

    override fun onTorchOff() {
        btFlash.text = "플래시켜기"
        isFlashOn = false
    }

    override fun onResume() {
        super.onResume()
        manager.onResume()
    }

    override fun onPause() {
        super.onPause()
        manager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        manager.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        manager.onSaveInstanceState(outState)
    }
}
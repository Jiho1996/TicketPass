package kr.com.ticketpass.hostmanage

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DecoratedBarcodeView.TorchListener
import kotlinx.android.synthetic.main.view_qrcode.*
import kr.com.ticketpass.R

class QReaderActivity : AppCompatActivity(), TorchListener {
    private lateinit var manager: CaptureManager
    private var isFlashOn = false // 플래시가 켜져 있는지
    private lateinit var btFlash: Button
    private lateinit var barcodeView: DecoratedBarcodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_entrance)
        barcodeView = findViewById(R.id.db_qr)
        manager = CaptureManager(this, barcodeView)
        manager!!.initializeFromIntent(intent, savedInstanceState)
        manager!!.decode()
        btFlash = findViewById(R.id.bt_flash)
        val intentIntegrator = IntentIntegrator(this)
        intentIntegrator.setBeepEnabled(false) //바코드 인식시 소리
        intentIntegrator.captureActivity = QReaderActivity::class.java
        intentIntegrator.initiateScan()
        bt_flash.setOnClickListener(View.OnClickListener {
            if (isFlashOn) {
                barcodeView.setTorchOff()
            } else {
                barcodeView.setTorchOn()
            }
        })
    }

    override fun onTorchOn() {
        btFlash!!.text = "플래시끄기"
        isFlashOn = true
    }

    override fun onTorchOff() {
        btFlash!!.text = "플래시켜기"
        isFlashOn = false
    }

    override fun onResume() {
        super.onResume()
        manager!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        manager!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        manager!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        manager!!.onSaveInstanceState(outState)
    }
}
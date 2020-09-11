package kr.com.ticketpass.guest

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import kr.com.ticketpass.model.TicketResponse

class LayoutTicket(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    private var expanded = false
    private var duration = 0

    fun isExpanded(): Boolean {
        return expanded
    }

    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
    }

    fun toggle() {
        if (expanded) expandView(this) else hideView(this)
    }

    private fun expandView(view: View) {
        view.measure(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        val targetHeight: Int = view.getMeasuredHeight()
        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        view.getLayoutParams().height = 1
        view.setVisibility(View.VISIBLE)
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                view.getLayoutParams().height =
                    if (interpolatedTime == 1f) targetHeight else (targetHeight * interpolatedTime).toInt()
                view.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        if (duration == -1) a.setDuration(
            (targetHeight / view.getContext().getResources()
                .getDisplayMetrics().density * 1.5).toLong()
        ) else a.duration =
            duration.toLong()
        view.startAnimation(a)
    }

    private fun hideView(view: View) {
        val initialHeight: Int = view.getMeasuredHeight()
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                if (interpolatedTime == 1f) {
                    view.setVisibility(View.GONE)
                } else {
                    view.getLayoutParams().height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        if (duration == -1) a.setDuration(
            (initialHeight / view.getContext().getResources()
                .getDisplayMetrics().density * 1.5).toLong()
        ) else a.duration =
            duration.toLong()
        view.startAnimation(a)
    }

    fun createQr(userId: String, ticketId: String, seat: String): Bitmap? {
        val data = "$userId $ticketId $seat"
        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,200,200)
        return BarcodeEncoder().createBitmap(bitMatrix)
    }
}
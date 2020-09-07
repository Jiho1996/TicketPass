package kr.com.ticketpass.guest

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.WindowManager
import android.view.animation.Animation
import androidx.constraintlayout.widget.ConstraintLayout


/**
 * LinearLayout that can expand and collapse
 */
class LayoutTicket(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private var expanded = false
    private var duration = 0

    init {
        val customValues: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ExpandableLinearLayout)
        duration = customValues.getInt(R.styleable.ExpandableLinearLayout_expandDuration, -1)
        customValues.recycle()
    }

    private fun init(attributeSet: AttributeSet?) {

    }

    fun isExpanded(): Boolean {
        return expanded
    }

    fun setExpanded(expanded: Boolean) {
        Log.e("layout", expanded.toString() + "")
        this.expanded = expanded
    }

    fun toggle() {
        if (expanded) expandView(this) else hideView(this)
    }

    private fun expandView(view: View) {
        view.measure(
            WindowManager.LayoutParams.MATCH_PARENT,
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
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
                .getDisplayMetrics().density * 1.5) as Int.toLong()
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
                .getDisplayMetrics().density * 1.5) as Int.toLong()
        ) else a.duration =
            duration.toLong()
        view.startAnimation(a)
    }
}
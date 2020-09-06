package kr.com.ticketpass.util

import android.content.Context
import android.widget.Toast

fun Context.toastUtil(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
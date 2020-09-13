package kr.com.ticketpass.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.com.ticketpass.model.SendEmailCodeForm
import kr.com.ticketpass.model.SignUpForm
import kr.com.ticketpass.network.requestApi
import kr.com.ticketpass.util.ConstValue
import kr.com.ticketpass.util.SharedPreferenceManager
import kr.com.ticketpass.util.SingleLiveEvent
import kr.com.ticketpass.util.sha256
import java.util.logging.Logger

class SignupViewModel: ViewModel() {
    var email: String = ""
    var password: String = ""
    var code: String = ""
    val emailError: SingleLiveEvent<Void> = SingleLiveEvent()
    val emailCodeSuccess: SingleLiveEvent<Void> = SingleLiveEvent()
    val signupSuccess: SingleLiveEvent<Void> = SingleLiveEvent()

    fun doSignup() {
        //뷰모델에 있는 이메일과 패스워드를 사용함
        requestApi.doSignup(SignUpForm(email, password.sha256(), code))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                SharedPreferenceManager.setToken(it.accessToken)
                SharedPreferenceManager.setPref(ConstValue.CONST_USER_ID, it.user.id)
                signupSuccess.call()
            }, {

            })
    }

    fun sendEmailCode(email: String) {
        requestApi.sendEmailCode(SendEmailCodeForm(email))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                emailError.call()
            }, {
                emailCodeSuccess.call()
                com.orhanobut.logger.Logger.d(it.localizedMessage)
            })
    }
}
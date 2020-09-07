package kr.com.ticketpass.viewmodel

import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.com.ticketpass.model.SignInForm
import kr.com.ticketpass.network.requestApi
import kr.com.ticketpass.util.SingleLiveEvent
import kr.com.ticketpass.util.isValidateEmail
import kr.com.ticketpass.util.isValidatePassword

class LoginViewModel : ViewModel() {
    val emailError: SingleLiveEvent<Void> = SingleLiveEvent()
    val passwordError: SingleLiveEvent<Void> = SingleLiveEvent()
    val HostSignUpButtonClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    val loginSuccess: SingleLiveEvent<Void> = SingleLiveEvent()

    fun doLogin(email: String, password: String, type: String) {
        // 아이디 검증과 패스워드 검증을 통과해야 api 호출 가능
        /*if (isValidateEmail(email) && isValidatePassword(password)) {
            requestApi.doLogin(SignInForm(email, password, type))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loginSuccess.call()
                }, {
                    Logger.d(it.localizedMessage)
                })
        }*/

        requestApi.doLogin(SignInForm(email, password, type))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loginSuccess.call()
            }, {
                Logger.d(it.localizedMessage)
            })
    }

    fun HostSignUpButtonClicked() {
        HostSignUpButtonClicked.call()
    }
}
package kr.com.ticketpass.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.com.ticketpass.model.SignUpForm
import kr.com.ticketpass.network.requestApi
import kr.com.ticketpass.util.SingleLiveEvent

class SignupViewModel: ViewModel() {
    var email: String = ""
    var password: String = ""
    var code: String = ""
    val emailError: SingleLiveEvent<Void> = SingleLiveEvent()
    val passwordError: SingleLiveEvent<Void> = SingleLiveEvent()
    val nextButtonClicked : SingleLiveEvent<Void> = SingleLiveEvent()

    fun doSignup() {
        //뷰모델에 있는 이메일과 패스워드를 사용함
        requestApi.doSignup(SignUpForm(email, password, code))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }

    fun sendEmailCode(email: String) {
        requestApi.sendEmailCode(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }
    fun nextButtonClicked(){
        nextButtonClicked.call()
    }
}
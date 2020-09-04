package kr.com.ticketpass.viewmodel

import androidx.lifecycle.ViewModel
import kr.com.ticketpass.util.SingleLiveEvent

class LoginViewModel: ViewModel() {
    val emailError: SingleLiveEvent<Void> = SingleLiveEvent()
    val passwordError: SingleLiveEvent<Void> = SingleLiveEvent()
    val HostSignUpButtonClicked : SingleLiveEvent<Void> = SingleLiveEvent()

    fun doLogin(email: String, password: String) {
        // 아이디 검증과 패스워드 검증을 통과해야 api 호출 가능
        if (isValidateEmail(email) && isValidatePassword(password)) {

        }
    }

    fun isValidateEmail(email: String): Boolean {
        //정규표현식 로직
        return false
    }

    fun isValidatePassword(password: String): Boolean {
        //정규표현식 로직
        return false
    }
    fun HostSignUpButtonClicked(){
        HostSignUpButtonClicked.call()
    }
}
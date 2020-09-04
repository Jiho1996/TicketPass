package kr.com.ticketpass.viewmodel

import androidx.lifecycle.ViewModel
import kr.com.ticketpass.util.SingleLiveEvent

class SignupViewModel: ViewModel() {
    var email: String = ""
    var password: String = ""
    val emailError: SingleLiveEvent<Void> = SingleLiveEvent()
    val passwordError: SingleLiveEvent<Void> = SingleLiveEvent()

    fun isValidateEmailAndNumber(email: String, number: Int) {
        //이메일과 인증번호 검사
        //통과하면 이메일에 이메일 값 저장
    }

    fun isValidatePassword(password: String, rePassword: String) {
        //비밀번호 정규식에 맞는지
        //비밀번호와 비밀번호 확인 일치 여부
    }

    fun doSignup() {
        //뷰모델에 있는 이메일과 패스워드를 사용함
    }
}
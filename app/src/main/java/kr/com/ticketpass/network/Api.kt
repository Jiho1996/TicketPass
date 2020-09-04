package kr.com.ticketpass.network

import io.reactivex.Single
import kr.com.ticketpass.model.SignInForm
import kr.com.ticketpass.model.SignUpForm
import kr.com.ticketpass.model.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @POST("/v1/auth/sign-up")
    fun doSignup(
        @Body signUpForm: SignUpForm
    ) : Single<SignupResponse>

    @POST("/v1/auth/sign-in")
    fun doLogin(
        @Body signInForm: SignInForm
    ) : Single<SignupResponse>

    @POST("/v1/auth/send-email-code")
    fun sendEmailCode(
        @Body email: String
    ) : Single<Void>
}
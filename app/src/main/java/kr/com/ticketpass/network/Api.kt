package kr.com.ticketpass.network

import io.reactivex.Single
import kr.com.ticketpass.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

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
        @Body sendEmailCodeForm: SendEmailCodeForm
    ) : Single<Response<String>>

    @POST("/v1/concerts")
    fun postConcert(
        @Header("Authorization") authorization: String,
        @Body newConcertForm: NewConcertForm
    ) : Single<NewConcertForm>

    @PUT("/v1/concerts/{concertId}")
    fun putConcert(
        @Header("Authorization") authorization: String,
        @Path("concertId") concertId: Int,
        @Body topImage: String,
        bottomImage: String,
        name: String,
        startTime: String,
        endTime: String,
        startDate: String
    ) : Single<ConcertInfo>

    @GET("/v1/concerts/{concertId}")
    fun getConcert(
        @Header("Authorization") authorization: String,
        @Path("concertId") concertId: String
    ) : Single<ConcertInfo>

    @POST("/v1/sheet/{spreadsheetId}/sync")
    fun syncConcert(
        @Header("Authorization") authorization: String,
        @Path("spreadsheetId") spreadsheetId : String
    ) : Single<String>

    @GET("/v1/users/{userId}/concerts")
    fun getUserConcert(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: String,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ) : Single<ConcertListResponse>

    @GET("/v1/users/{userId}/tickets")
    fun getTickets(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: String,
        @Query("expiredSize") expiredSize: Int,
        @Query("unexpiredSize") unexpiredSize: Int
    ) : Single<TicketResponse>

    @POST("/v1/users/{userId}/use-ticket")
    fun postTickets(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: String,
        @Body qrData: QrForm
    ) : Single<TicketResponse.TicketInfo>

    @GET("/v1/users/{userId}/tickets/{ticketId}")
    fun getTicketQr(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: String,
        @Path("ticketId") ticketId: String
    ) : Single<TicketForm>
}
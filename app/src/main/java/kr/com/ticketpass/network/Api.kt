package kr.com.ticketpass.network

import io.reactivex.Single
import kr.com.ticketpass.model.*
import retrofit2.Call
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
        @Body email: String
    ) : Single<Void>

    @POST("/v1/concerts")
    fun postConcert(
        @Header("Authorization") authorization: String,
        @Body newConcertForm: NewConcertForm
    ) : Single<ConcertInfo>

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
        @Path("concertId") concertId: Int
    ) : Single<ConcertInfo>

    @POST("/v1/concerts/{concertId}/sync-tickets")
    fun syncConcert(
        @Header("Authorization") authorization: String,
        @Path("concertId") concertId: Int
    ) : Single<Void>

    @GET("/v1/users/{userId}/concerts")
    fun getUserConcert(
        @Header("Authorization") authorization: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Path("userId") userId: String
    ) : Single<List<ConcertInfo>>

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
        @Body qrData: String
    ) : Single<TicketResponse.TicketInfo>
}
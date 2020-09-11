package kr.com.ticketpass.network

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val requestApi: Api by lazy {

    Retrofit.Builder()
        .baseUrl("http://ticketpass.puroong.me")
        .client(getOkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}

private fun getOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(15L, TimeUnit.SECONDS)
        .writeTimeout(15L, TimeUnit.SECONDS)
        .readTimeout(15L, TimeUnit.SECONDS)
        .addInterceptor(provideLoggingInterceptor())
        .build()

private fun getLoggerInterceptor(): Interceptor = object: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request().newBuilder().build())
        val body: String = response.body?.string() ?: ""
        try {
            JSONObject(body)
            Logger.t("RetrofitManager").json(body)
        } catch (e: JSONException) {
            Logger.t("RetrofitManager").d(body)
        } finally {
            return response.newBuilder()
                .body(
                    ResponseBody.create(
                        response.body?.contentType(),
                        body))
                .build()
        }
    }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

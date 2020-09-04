package kr.com.ticketpass.network

import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val requestApi: Api by lazy {
    Retrofit.Builder()
        .baseUrl("http://ticketpass.puroong.me/")
        .client(provideOkHttpClient(provideLoggingInterceptor()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}

private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    val b = OkHttpClient.Builder()
    b.addInterceptor { chain ->
        Logger.d(chain.proceed(chain.request().newBuilder().build()))
        return@addInterceptor chain.proceed(chain.request().newBuilder().build())
    }
    b.addInterceptor(interceptor)
    b.connectTimeout(1, TimeUnit.MINUTES)
    b.writeTimeout(1, TimeUnit.MINUTES)
    b.readTimeout(1, TimeUnit.MINUTES)
    return b.build()
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor
        = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

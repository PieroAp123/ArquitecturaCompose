package com.everis.data.network.di

import android.content.Context
import com.everis.data.network.ApiConfig
import com.everis.data.network.repository.LoginRepository
import com.everis.data.network.utils.AUTHORIZATION
import com.everis.data.network.utils.BASE_URL
import com.everis.data.network.utils.PLATFORM
import com.everis.data.network.utils.TIMEOUT
import com.everis.data.preference.manager.PreferencesManager
import com.everis.data.preference.utils.PREFERENCE_TOKEN
import com.everis.domain.repository.LoginRepositoryNetwork
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit
import javax.net.ssl.KeyManager
import javax.net.ssl.*

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

val networkModule = module {

    single { providerHttpLoggingInterceptor() }
    single { providerCache(get()) }
    single { ApiInterceptor(get(), get()) }
    single { ConnectionFactory(get()) }
    single { providerOkHttpClient(get(), get(), get()) }
    single { providerRetrofit(getProperty(BASE_URL), get()) }
    single { providerApi(get()) }

    single<LoginRepositoryNetwork> { LoginRepository(get()) }
}

fun providerApi(retrofit: Retrofit): ApiConfig {
    return retrofit.create(ApiConfig::class.java)
}

fun providerRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build()
}

fun providerOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apiInterceptor: ApiInterceptor,
    connectionFactory: ConnectionFactory
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(apiInterceptor)
        .sslSocketFactory(
            connectionFactory.getSSLSocketFactory(),
            connectionFactory.getX509TrustManagerV2()
        )
        .build()
}

fun providerCache(context: Context): Cache {
    val cacheSize: Long = 10485760
    return Cache(context.cacheDir, cacheSize)
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    //logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

class ApiInterceptor(private val context: Context, private val sharedPreferences: PreferencesManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", AUTHORIZATION + sharedPreferences.getString(PREFERENCE_TOKEN))
            .header("x-os", PLATFORM)
            /*.header("x-density", getDensity(context).toString())
            .header("x-width", getWidht(context).toString())
            .header("x-height", getHeight(context).toString())*/
            .build()
        return chain.proceed(request)
    }
}


class ConnectionFactory(private val keyManager: Array<KeyManager>? = null) {

    private var sslSocketFactory: SSLSocketFactory? = null
    private var x509TrustManager: X509TrustManager? = null

    fun getSSLSocketFactory(): SSLSocketFactory {
        if (sslSocketFactory == null) {
            try {
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(keyManager, null, null)
                sslSocketFactory = sslContext.socketFactory
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException(e)
            } catch (e: KeyManagementException) {
                throw RuntimeException(e)
            }
        }
        return sslSocketFactory!!
    }

    //@Throws(Exception::class)
    fun getX509TrustManagerV2(): X509TrustManager {
        if (x509TrustManager == null) {
            val trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            for (trustManager in trustManagerFactory.trustManagers) {
                if (trustManager is X509TrustManager) {
                    x509TrustManager = trustManager
                    break
                }
            }
        }
        return x509TrustManager!!
    }
}
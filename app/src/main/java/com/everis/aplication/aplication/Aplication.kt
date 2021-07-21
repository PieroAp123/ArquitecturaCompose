package com.everis.aplication.aplication

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import com.everis.aplication.di.viewModelsModule
import com.everis.data.network.di.networkModule
import com.everis.data.network.utils.BASE_URL
import com.everis.data.preference.di.preferenceModule
import com.everis.domain.di.domainModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Aplication: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Aplication)
            modules(listOf(preferenceModule, networkModule, domainModule, viewModelsModule))
        }
        getKoin().setProperty(BASE_URL, "https://www.codeapp.us/")
    }

}
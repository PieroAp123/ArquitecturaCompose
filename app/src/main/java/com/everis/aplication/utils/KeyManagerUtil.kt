package com.everis.aplication.utils

import android.app.Application
import com.everis.aplication.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.security.KeyStore
import javax.net.ssl.KeyManager
import javax.net.ssl.KeyManagerFactory

/**
 * Created by WilderCs on 2019-10-23.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

class KeyManagerUtil(private val context: Application) {

    fun getKeyManager(): Array<KeyManager>? {
        var keyManagers: Array<KeyManager>? = null
        try {
            val assetManager = context.assets
            val store = KeyStore.getInstance("pkcs12")
            val isClient = assetManager.open(context.getString(R.string.certificate_location))
            isClient.use { isClient ->
                store.load(isClient, context.getString(R.string.certificate_password).toCharArray())
                val kmf = KeyManagerFactory.getInstance("PKIX")
                kmf.init(store, context.getString(R.string.certificate_password).toCharArray())
                keyManagers = kmf.keyManagers
            }
            return keyManagers
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}
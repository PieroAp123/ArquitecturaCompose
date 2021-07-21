package com.everis.aplication.screens

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everis.aplication.utils.DispatcherProvider
import com.everis.domain.entities.CustomError
import com.everis.domain.entities.CustomResult
import com.everis.domain.entities.User
import com.everis.domain.extensions.toError
import com.everis.domain.usecases.LoginUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

open class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val dispatcherProvider: DispatcherProvider = DispatcherProvider()
) : ViewModel() {

    val userLiveData = MutableLiveData<User>()
    val errorLiveData = MutableLiveData<CustomError>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun doLogin(username: String, password: String) {

        GlobalScope.launch(dispatcherProvider.IO + CoroutineExceptionHandler { _, ex ->
            errorLiveData.postValue(ex.toError())
            loadingLiveData.postValue(false)
        }) {
            loadingLiveData.postValue(true)
            when (val user = loginUseCase.doLogin(username, password)) {
                is CustomResult.OnSuccess -> {
                    userLiveData.postValue(user.data!!)
                    //Log.e("Ingresó", "Correcto Ingreso")
                }
                is CustomResult.OnError -> {
                    errorLiveData.postValue(user.error)
                }
            }
            loadingLiveData.postValue(false)
        }
    }

}
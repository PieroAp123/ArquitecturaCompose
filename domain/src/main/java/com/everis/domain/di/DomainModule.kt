package com.everis.domain.di

import com.everis.domain.usecases.LoginUseCase
import org.koin.dsl.module

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

val domainModule = module {
    single { LoginUseCase(get(), get()) }
}




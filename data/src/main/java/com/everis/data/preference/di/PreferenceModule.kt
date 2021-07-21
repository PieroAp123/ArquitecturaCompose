package com.everis.data.preference.di

import com.everis.data.preference.UserPreference
import com.everis.data.preference.manager.PreferencesManager
import com.everis.domain.repository.LoginRepositoryPreference
import org.koin.dsl.module

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/


val preferenceModule = module {
    single { PreferencesManager(get()) }
    single<LoginRepositoryPreference> { UserPreference(get()) }
}
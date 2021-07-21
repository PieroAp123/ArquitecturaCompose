package com.everis.data.preference

import com.everis.data.preference.manager.PreferencesManager
import com.everis.domain.entities.User
import com.everis.domain.repository.LoginRepositoryPreference

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

class UserPreference(private val sharedPreferenceManager: PreferencesManager) :
    LoginRepositoryPreference {

    override fun saveUser(user: User) {
    }

    override fun saveIsLogin(flag: Boolean) {
    }

    override fun getUser(): User {
        return User()
    }

    override fun isLogin(): Boolean {
        return sharedPreferenceManager.getBoolean("")
    }

}
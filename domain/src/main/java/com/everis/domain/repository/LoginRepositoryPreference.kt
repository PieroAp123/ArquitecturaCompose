package com.everis.domain.repository

import com.everis.domain.entities.User

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

interface LoginRepositoryPreference {
    fun saveUser(user: User)
    fun saveIsLogin(flag: Boolean)
    fun getUser(): User
    fun isLogin(): Boolean
}
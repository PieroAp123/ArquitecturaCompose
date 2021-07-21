package com.everis.domain.repository

import com.everis.domain.entities.CustomResult
import com.everis.domain.entities.User

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

interface LoginRepositoryNetwork {
    fun doLogin(username: String, password: String): CustomResult<User>
    //fun doLogin(username: String, password: String): CustomResult<String>
}
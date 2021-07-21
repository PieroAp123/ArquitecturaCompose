package com.everis.domain.entities

data class User(
    var userName: String? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var token: String? = null
)
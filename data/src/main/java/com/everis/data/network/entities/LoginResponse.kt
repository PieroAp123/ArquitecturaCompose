package com.everis.data.network.entities



import android.widget.Toast
import com.everis.domain.entities.User
import com.google.gson.annotations.SerializedName
/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 WM Everis. All rights reserved.
 **/

/*data class UserResponse2(
    @SerializedName("token")
    val token: String
)

data class RegisterResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("token")
    var token: String
)*/

data class LoginResponse(

    @field:SerializedName("user")
    val user: UserResponse? = null,

    @field:SerializedName("status")
    var status: StatusResponse? = null
) {
    companion object {
        fun toUser(response: LoginResponse): User {
            if (response.user == null)
                return User()
            return response.user.toUser()
        }
    }
}

data class UserResponse(
    @field:SerializedName("username")
    var userName: String? = null,

    @field:SerializedName("email")
    var email: String? = null,

    @field:SerializedName("first_name")
    var firstName: String? = null,

    @field:SerializedName("last_name")
    var lastName: String? = null,

    @field:SerializedName("token")
    var token: String? = null
){
    fun toUser() = User(userName, email, firstName, lastName, token)
}

data class StatusResponse(
    @field:SerializedName("code")
    var code: Int = 0,

    @field:SerializedName("message")
    var message: String? = null
)

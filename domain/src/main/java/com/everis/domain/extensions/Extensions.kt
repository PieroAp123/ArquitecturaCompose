package com.everis.domain.extensions

import com.everis.domain.entities.*
import java.net.UnknownHostException

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

fun CustomError.toThrowable(): ErrorThrowable {
    return ErrorThrowable(code, message, cause)
}

fun Throwable.toError(): CustomError {
    return when (this) {
        is ErrorThrowable -> this.toError()
        is UnknownHostException -> NetworkError(message = this.message, cause = this)
        else -> UnknownError(message = this.message, cause = this)
    }
}

/**
 * A convenient method for wrapping a [Throwable] in a[CustomResult].
 * Converts a [Throwable] to a [CustomResult.OnError].
 */
/*fun <T> Throwable.toResult(): CustomResult<T> {
    return when (this) {
        is ErrorThrowable -> CustomResult.OnError(this.toError())
        is UnknownHostException -> CustomResult.OnError(NetworkError(message = this.message, cause = this))
        else -> CustomResult.OnError(UnknownError(message = this.message, cause = this))
    }
}*/
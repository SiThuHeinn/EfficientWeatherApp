package com.sithuheinn.mm.data.remote

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Enumeration class `ErrorCodes` defines standard HTTP error codes along with their corresponding integer values.
 * Each enum constant represents a specific HTTP error code commonly encountered in network communication.
 *
 * @property code The integer value associated with the HTTP error code.
 */
enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1),
    BadRequest(400),
    NotFound(404),
    Conflict(409),
    InternalServerError(500),
    Forbidden(403),
    NotAcceptable(406),
    ServiceUnavailable(503),
    UnAuthorized(401),
}

/**
 * Handles exceptions that may occur during network operations and maps them to custom error messages
 * based on the type of exception encountered.
 *
 * @param throwable The exception thrown during the network operation.
 * @return A custom error message indicating the reason for the exception.
 */
fun handleException(throwable: Exception): RemoteResource.CustomMessages {
    return when (throwable) {
        is HttpException -> RemoteResource.CustomMessages.HttpException
        is TimeoutException -> RemoteResource.CustomMessages.Timeout
        is UnknownHostException -> RemoteResource.CustomMessages.ServerError
        is ConnectException -> RemoteResource.CustomMessages.NoInternet
        is SocketTimeoutException -> RemoteResource.CustomMessages.SocketTimeOutException
        else -> RemoteResource.CustomMessages.SomethingWentWrong("Error:${throwable.message ?: throwable.localizedMessage ?: "Something Went Wrong"}")
    }
}

/**
 * Handles exceptions by associating an HTTP status code with a custom error message.
 * It uses the `getErrorType` function to determine the appropriate error message based on the status code.
 *
 * @param statusCode The HTTP status code received in the response.
 * @param message Additional information or error message received along with the status code.
 * @return A custom error message corresponding to the HTTP status code.
 */
fun handleException(statusCode: Int, message: String): RemoteResource.CustomMessages {
    return getErrorType(statusCode, message)
}

/**
 * Determines the appropriate custom error message based on the provided HTTP status code.
 *
 * @param code The HTTP status code received in the response.
 * @param message Additional information or error message received along with the status code.
 * @return A custom error message corresponding to the HTTP status code.
 */
private fun getErrorType(code: Int, message: String): RemoteResource.CustomMessages {
    return when (code) {
        ErrorCodes.SocketTimeOut.code -> RemoteResource.CustomMessages.Timeout
        ErrorCodes.UnAuthorized.code -> RemoteResource.CustomMessages.Unauthorized
        ErrorCodes.InternalServerError.code -> RemoteResource.CustomMessages.InternalServerError
        ErrorCodes.BadRequest.code -> RemoteResource.CustomMessages.BadRequest
        ErrorCodes.Conflict.code -> RemoteResource.CustomMessages.Conflict
        ErrorCodes.NotFound.code -> RemoteResource.CustomMessages.NotFound
        ErrorCodes.NotAcceptable.code -> RemoteResource.CustomMessages.NotAcceptable
        ErrorCodes.ServiceUnavailable.code -> RemoteResource.CustomMessages.ServiceUnavailable
        ErrorCodes.Forbidden.code -> RemoteResource.CustomMessages.Forbidden
        else -> RemoteResource.CustomMessages.SomethingWentWrong(message)
    }
}

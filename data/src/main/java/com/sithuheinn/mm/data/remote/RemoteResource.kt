package com.sithuheinn.mm.data.remote

import retrofit2.Response


/**
 * A sealed class representing the result of a remote API call, encapsulating either successful
 * data, loading events, or error events. It also includes a set of predefined custom error
 * messages to provide additional context in case of an error.
 *
 * @param T The type of data encapsulated by the remote resource.
 * @property data The data payload of the remote resource, representing the result of a successful operation.
 * @property message A custom error message indicating the reason for an error event.
 */
sealed class RemoteResource<T>(
    val data: T? = null,
    val message: CustomMessages = CustomMessages.SomethingWentWrong("Something Went Wrong")
) {

    class LoadingEvent<T> : RemoteResource<T>() // Subclass representing a loading event during an API call.
    class SuccessEvent<T>(data: T) : RemoteResource<T>(data)  // Subclass representing a successful outcome of an API call.
    class ErrorEvent<T>(errorMessage: CustomMessages) : RemoteResource<T>(null, errorMessage) // Subclass representing an error outcome of an API call with an associated error message.

    /**
     * Sealed class defining custom error messages for various scenarios during an API call.
     *
     * @property message A descriptive message associated with a specific error scenario.
     */
    sealed class CustomMessages(val message: String = "") {

        object Timeout : CustomMessages() // Object representing a timeout error.
        object EmptyData : CustomMessages() // Object representing an empty data error.
        object ServerError : CustomMessages() // Object representing a server error.
        object HttpException : CustomMessages() // Object representing an HTTP exception.
        object SocketTimeOutException : CustomMessages()  // Object representing a socket timeout exception.
        object NoInternet : CustomMessages() // Object representing a no internet connection error.
        object Unauthorized : CustomMessages() // Object representing an unauthorized access error.
        object InternalServerError : CustomMessages() // Object representing an internal server error.
        object BadRequest : CustomMessages() // Object representing a bad request error.
        object Conflict : CustomMessages() // Object representing a conflict error.
        object NotFound : CustomMessages() // Object representing a not found error.
        object NotAcceptable : CustomMessages() // Object representing a not acceptable error.
        object ServiceUnavailable : CustomMessages() // Object representing a service unavailable error.
        object Forbidden : CustomMessages() // Object representing a forbidden error.
        data class SomethingWentWrong(val error: String) : CustomMessages(message = error) // Data class representing a generic "Something Went Wrong" error with an additional error message.
    }

    /**
     * Retrieves a user-friendly error message based on the type of error stored in the 'message' property.
     *
     * @return A user-friendly error message corresponding to the error scenario.
     */
    fun getErrorMessage(): String {
        return when (message) {
            is CustomMessages.BadRequest -> message.message
            CustomMessages.Conflict -> "Conflict"
            CustomMessages.EmptyData -> "Empty Data"
            CustomMessages.Forbidden -> "Forbidden"
            CustomMessages.HttpException -> "HttpException"
            CustomMessages.InternalServerError -> "Internal Server Error"
            CustomMessages.NoInternet -> "No Internet"
            CustomMessages.NotAcceptable -> "NotAcceptable"
            CustomMessages.NotFound -> message.message
            CustomMessages.ServerError -> "Server Error"
            CustomMessages.ServiceUnavailable -> "Service Unavailable"
            CustomMessages.SocketTimeOutException -> "SocketTimeOutException"
            is CustomMessages.SomethingWentWrong -> "Something Went Wrong"
            CustomMessages.Timeout -> "Timeout"
            CustomMessages.Unauthorized -> "Unauthorized"
        }
    }
}

/**
 * A suspending function for making a safe API call. It handles exceptions during the API call
 * and returns a RemoteResource encapsulating the result.
 *
 * @param apiCall The suspend function representing the API call.
 * @return A RemoteResource containing either successful data or an error event.
 */
suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): RemoteResource<T> {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body() ?: return RemoteResource.ErrorEvent(RemoteResource.CustomMessages.EmptyData)
            return RemoteResource.SuccessEvent(body)
        }
        return RemoteResource.ErrorEvent(
            handleException(response.code(), response.errorBody().toString())
        )
    } catch (e: Exception) {
        return RemoteResource.ErrorEvent(handleException(e))
    }
}

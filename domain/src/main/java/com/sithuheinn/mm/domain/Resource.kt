package com.sithuheinn.mm.domain


/**
 * A sealed class representing a generic resource, encapsulating the result of an operation
 * that can either be successful or encounter an error. It includes a data payload and an
 * optional message to provide additional context.
 *
 * @param T The type of data encapsulated by the resource.
 * @property data The data payload of the resource, representing the result of a successful operation.
 * @property message An optional message providing additional context or an error description.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    /**
     * A subclass of Resource representing a successful outcome of an operation.
     *
     * @param T The type of data encapsulated by the resource.
     * @property data The data payload of the successful resource.
     */
    class Success<T>(data: T?): Resource<T>(data)

    /**
     * A subclass of Resource representing an error outcome of an operation.
     *
     * @param T The type of data encapsulated by the resource.
     * @property message A message describing the error or providing additional context.
     * @property data The data payload associated with the error, if applicable.
     */
    class Error<T>(message: String, data:T? = null): Resource<T>(data, message)
}
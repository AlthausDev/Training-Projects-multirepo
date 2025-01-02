package com.althaus.dev.cinemaNexus.core.errors

import android.content.Context
import com.althaus.dev.cinemaNexus.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.io.IOException

/**
 * Maneja errores en Flows.
 * - Captura IOException para DataStore y emite valores predeterminados.
 * - Propaga otras excepciones.
 */

fun <T> Flow<T>.handleFlowErrors(defaultValue: T): Flow<T> {
    return catch { exception ->
        when (exception) {
            is IOException -> emit(defaultValue)
            else -> throw exception
        }
    }
}


/**
 * Maneja errores generales y permite ejecutar lógica personalizada.
 * @param onError Acción personalizada para manejar la excepción.
 * @return Resultado o null si ocurrió un error.
 */

fun <T> handleErrors(onError: (Throwable) -> Unit, block: () -> T): T? {
    return try {
        block()
    } catch (e: Throwable) {
        onError(e)
        null
    }
}

/**
 * Maneja errores comunes de APIs (HTTP).
 */
fun handleHttpError(context: Context, exception: HttpException): String {
    return when (exception.code()) {
        400 -> context.getString(R.string.error_bad_request)
        401 -> context.getString(R.string.error_unauthorized)
        403 -> context.getString(R.string.error_forbidden)
        404 -> context.getString(R.string.error_not_found)
        500 -> context.getString(R.string.error_internal_server)
        else -> context.getString(R.string.error_unknown, exception.code())
    }
}
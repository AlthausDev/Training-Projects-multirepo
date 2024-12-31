package com.althaus.dev.cinemaNexus.utils

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
fun handleHttpError(exception: HttpException): String {
    return when (exception.code()) {
        400 -> "Solicitud incorrecta. Verifica los datos enviados."
        401 -> "No autorizado. Por favor, inicia sesión."
        403 -> "Prohibido. No tienes acceso a este recurso."
        404 -> "Recurso no encontrado."
        500 -> "Error interno del servidor. Intenta nuevamente más tarde."
        else -> "Error desconocido: ${exception.code()}"
    }
}
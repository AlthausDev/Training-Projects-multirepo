package com.althaus.dev.project04_cartelera.data.repository

import com.althaus.dev.project04_cartelera.data.model.User

interface UserRepository {
    suspend fun authenticate(username: String, password: String): User?
    suspend fun register(user: User): Boolean
}

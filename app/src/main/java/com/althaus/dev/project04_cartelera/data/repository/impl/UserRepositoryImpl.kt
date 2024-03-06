package com.althaus.dev.project04_cartelera.data.repository.impl

import com.althaus.dev.project04_cartelera.data.dao.UserDao
import com.althaus.dev.project04_cartelera.data.model.User
import com.althaus.dev.project04_cartelera.data.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun authenticate(username: String, password: String): User? {
        return userDao.authenticate(username, password)
    }

    override suspend fun register(user: User): Boolean {
        val existingUser = userDao.getUserByUsername(user.username)
        if (existingUser != null) {
            return false
        }
        userDao.insertUser(user)
        return true
    }
}
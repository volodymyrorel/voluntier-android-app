package uzhnu.volodymyrorel.voluntier.domain.auth

import uzhnu.volodymyrorel.voluntier.domain.auth.entity.User

interface AuthRepository {

    suspend fun login(email: String, password: String): User?

    fun isLoggedIn(): Boolean
}
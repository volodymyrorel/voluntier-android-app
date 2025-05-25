package uzhnu.volodymyrorel.voluntier.domain.auth

import uzhnu.volodymyrorel.voluntier.domain.auth.entity.User

interface AuthRepository {

    suspend fun login(email: String, password: String): User?

    fun isLoggedIn(): Boolean

    fun logOut()

    suspend fun signUp(email: String, password: String): User?

    suspend fun createUser(_id: String, email: String, surname: String, name: String, fatherName: String): String?

    suspend fun createOrganization(_id: String, email: String, publicName: String, govName: String, type: String, code: String): String?
}
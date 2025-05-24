package uzhnu.volodymyrorel.voluntier.domain.auth

interface AuthRepository {

    suspend fun login(email: String, password: String)

    fun isLoggedIn(): Boolean
}
package uzhnu.volodymyrorel.voluntier.data.auth

import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    override suspend fun login(email: String, password: String) {

    }
}
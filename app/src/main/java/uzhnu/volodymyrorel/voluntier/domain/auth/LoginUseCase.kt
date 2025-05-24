package uzhnu.volodymyrorel.voluntier.domain.auth

import androidx.datastore.preferences.protobuf.JavaType
import androidx.datastore.preferences.protobuf.NullValue
import org.jetbrains.annotations.NotNull
import uzhnu.volodymyrorel.voluntier.domain.auth.entity.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    val authRepository: AuthRepository,
    val authHelper: AuthHelper
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ) : User? {
        val response = authRepository.login(email, password)
        if (response != null) {
            authHelper.storeUser(response)
        }
        return response
    }
}
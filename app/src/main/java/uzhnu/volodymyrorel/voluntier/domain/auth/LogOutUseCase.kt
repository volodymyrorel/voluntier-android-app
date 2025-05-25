package uzhnu.volodymyrorel.voluntier.domain.auth

import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    val authRepository: AuthRepository,
    val authHelper: AuthHelper
) {

    suspend operator fun invoke() {
        authRepository.logOut()
        authHelper.logOut()
    }
}
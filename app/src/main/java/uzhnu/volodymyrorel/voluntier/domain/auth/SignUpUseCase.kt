package uzhnu.volodymyrorel.voluntier.domain.auth

import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    val authRepository: AuthRepository,
    val authHelper: AuthHelper
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        isOrganization: Boolean,
        userSurname: String,
        userName: String,
        userFatherName: String,
        orgPublicName: String,
        orgGovName: String,
        orgType: String,
        orgCode: String
    ) : String? {
        authRepository.logOut()
        authHelper.logOut()
        val response = authRepository.signUp(email = email, password = password)
        if (response == null) {
            return null
        }
        authHelper.storeUser(response)
        if (isOrganization) {
            val orgId = authRepository.createOrganization(
                _id = response.id,
                email = email,
                publicName = orgPublicName,
                govName = orgGovName,
                type = orgType,
                code = orgCode
            )
            return orgId
        } else {
            val userId = authRepository.createUser(
                _id = response.id,
                email = email,
                surname = userSurname,
                name = userName,
                fatherName = userFatherName
            )
            return userId
        }
    }
}
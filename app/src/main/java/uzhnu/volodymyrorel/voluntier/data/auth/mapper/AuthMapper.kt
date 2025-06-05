package uzhnu.volodymyrorel.voluntier.data.auth.mapper

import com.google.firebase.firestore.DocumentSnapshot
import uzhnu.volodymyrorel.voluntier.domain.auth.entity.UserData
import javax.inject.Inject

class AuthMapper @Inject constructor() {

    fun mapToUserData(document: DocumentSnapshot): UserData {
        return UserData(
            id = document.id,
            email = document.data?.get("email") as String? ?: "",
            surname = document.data?.get("userSurname") as String? ?: "",
            name = document.data?.get("userName") as String? ?: "",
            fatherName = document.data?.get("userFatherName") as String? ?: ""
        )
    }
}
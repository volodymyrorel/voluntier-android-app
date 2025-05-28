package uzhnu.volodymyrorel.voluntier.domain.auth

import com.google.firebase.firestore.DocumentSnapshot
import uzhnu.volodymyrorel.voluntier.domain.auth.entity.User

interface AuthHelper {

    val user: User

    fun isLoggedIn(): Boolean

    suspend fun storeUser(user: User)

    suspend fun logOut()

    suspend fun getCurrentUserData(): DocumentSnapshot?
}
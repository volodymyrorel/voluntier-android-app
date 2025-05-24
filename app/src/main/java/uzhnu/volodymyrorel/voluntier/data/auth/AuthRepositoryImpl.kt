package uzhnu.volodymyrorel.voluntier.data.auth

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.auth.entity.User
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class AuthRepositoryImpl @Inject constructor(
    private val navigator: Navigator
) : AuthRepository {

    private val auth = Firebase.auth

    override suspend fun login(email: String, password: String): User? {
        return suspendCoroutine { continuation ->
            auth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("test", "User logged in. UID: ${auth.currentUser?.uid}")
                        continuation.resume(User(auth.currentUser!!.uid, email))
                    } else {
                        Log.d("test", "User is not logged in")
                        continuation.resume(null)
                    }
                }
        }

    }

    override fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}
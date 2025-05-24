package uzhnu.volodymyrorel.voluntier.data.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val navigator: Navigator
) : AuthRepository {

    private val auth = Firebase.auth

    override suspend fun login(email: String, password: String) {
        auth
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d("test", "User logged in. UID: ${auth.currentUser?.uid}")
                navigator.navigateToHomeScreen()
            }
            .addOnFailureListener {
                Log.d("test", "User is not logged in")
            }
    }

    override fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}
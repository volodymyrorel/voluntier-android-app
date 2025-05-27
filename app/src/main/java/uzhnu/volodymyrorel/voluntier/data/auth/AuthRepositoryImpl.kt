package uzhnu.volodymyrorel.voluntier.data.auth

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.auth.entity.User
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class AuthRepositoryImpl @Inject constructor(
    private val navigator: Navigator,
    val firestore: FirebaseFirestore
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

    override fun logOut() {
        auth.signOut()
    }

    override suspend fun signUp(email: String, password: String): User? {
        return suspendCoroutine { continuation ->
            auth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("test", "User was created. ID: ${task.result.user!!.uid}")
                        continuation.resume(User(task.result.user!!.uid, task.result.user!!.email!!))
                    } else {
                        Log.d("test", "SignUp error: ${task.result}")
                        continuation.resume(null)
                    }
                }
        }
    }

    override suspend fun getCurrentUserRole(): String {
        return suspendCoroutine { continuation ->
            firestore
                .collection("users")
                .document(auth.currentUser!!.uid)
                .get()
                .addOnSuccessListener { response ->
                    continuation.resume(response.data?.get("role") as String)
                }
                .addOnFailureListener { error ->
                    continuation.resume("user")
                }
        }
    }

    override suspend fun createUser(_id: String, email: String, surname: String, name: String, fatherName: String) : String? {
        return suspendCoroutine { continuation ->
            val userDocument = hashMapOf(
                "email" to email,
                "role" to "user",
                "userSurname" to surname,
                "userName" to name,
                "userFatherName" to fatherName,
                "isVerified" to false,
                "createdAt" to System.currentTimeMillis(),
                "updatedAt" to System.currentTimeMillis()
            )
            firestore
                .collection("users")
                .document(_id)
                .set(userDocument)
                .addOnSuccessListener {
                    Log.d("test", "User data has been stored. User ID: ${_id}")
                    continuation.resume(_id)
                }
                .addOnFailureListener { error ->
                    Log.d("test", "User data not stored. Error: ${error.message}")
                    continuation.resume(null)
                }
        }
    }

    override suspend fun createOrganization(_id: String, email: String, publicName: String, govName: String, type: String, code: String): String? {
        return suspendCoroutine { continuation ->
            val orgDocument = hashMapOf(
                "email" to email,
                "role" to "organization",
                "orgPublicName" to publicName,
                "orgGovName" to govName,
                "orgType" to type,
                "orgCode" to code,
                "isVerified" to false,
                "createdAt" to System.currentTimeMillis(),
                "updatedAt" to System.currentTimeMillis()
            )
            firestore
                .collection("users")
                .document(_id)
                .set(orgDocument)
                .addOnSuccessListener {
                    Log.d("test", "Organization data has been stored. Org ID: ${_id}")
                    continuation.resume(_id)
                }
                .addOnFailureListener { error ->
                    Log.d("test", "Organization data not stored. Error: ${error.message}")
                    continuation.resume(null)
                }
        }
    }
}
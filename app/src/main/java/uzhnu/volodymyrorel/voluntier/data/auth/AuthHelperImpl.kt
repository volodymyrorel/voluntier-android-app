package uzhnu.volodymyrorel.voluntier.data.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import uzhnu.volodymyrorel.voluntier.data.auth.mapper.AuthMapper
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.auth.entity.User
import uzhnu.volodymyrorel.voluntier.domain.auth.entity.UserData
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class AuthHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private var authRepository: AuthRepository,
    private val firestore: FirebaseFirestore,
    private val authMapper: AuthMapper
) : AuthHelper {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)
    private var _user: User? = null

    init {
        runBlocking {
            val userJson = context.dataStore.data.first()[Keys.user]
            if (userJson.isNullOrBlank()) return@runBlocking
            _user = Json.decodeFromString<User>(userJson)
        }
    }

    override val user: User; get() = requireNotNull(_user)

    override fun isLoggedIn(): Boolean {
        return authRepository.isLoggedIn() && _user != null
    }

    override suspend fun storeUser(user: User) {
        context.dataStore.edit { preferences ->
            preferences[Keys.user] = Json.encodeToString(user)
        }
        _user = user
    }

    override suspend fun logOut() {
        context.dataStore.edit {
            it.remove(Keys.user)
        }
        _user = null
    }

    override suspend fun getCurrentUserData(): DocumentSnapshot? {
        return suspendCoroutine { continuation ->
            firestore
                .collection("users")
                .document(Firebase.auth.currentUser!!.uid)
                .get()
                .addOnSuccessListener { response ->
                    continuation.resume(response)
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }

    override suspend fun getUserData(userId: String): UserData? {
        return suspendCoroutine { continuation ->
            firestore
                .collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { response ->
                    continuation.resume(authMapper.mapToUserData(response))
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }

    private companion object {
        const val PREFERENCES_NAME = "auth_preferences"
    }

    private object Keys {
        val user = stringPreferencesKey("user")
    }
}
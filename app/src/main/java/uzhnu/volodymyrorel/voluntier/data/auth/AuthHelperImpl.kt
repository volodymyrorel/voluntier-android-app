package uzhnu.volodymyrorel.voluntier.data.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.auth.entity.User
import javax.inject.Inject

internal class AuthHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
//    private var authRepository: AuthRepository
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
        return false
//        return authRepository.isLoggedIn() && _user != null
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

    private companion object {
        const val PREFERENCES_NAME = "auth_preferences"
    }

    private object Keys {
        val user = stringPreferencesKey("user")
    }
}
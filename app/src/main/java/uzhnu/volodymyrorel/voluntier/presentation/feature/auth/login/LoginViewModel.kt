package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.auth.LoginUseCase
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginStateUi.DEFAULT)
    val state: StateFlow<LoginStateUi>; get() = _state

    fun onLoginClicked() {
        val state = state.value
        if (!areFieldsValid(state.email, state.password)) return
        viewModelScope.launch {
            val login = loginUseCase(state.email, state.password)
            if (login != null) navigator.navigateToMainScreen()
        }
    }

    fun onSignUpClicked() {
        navigator.navigateToSignUpScreen()
    }

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun areFieldsValid(email: String, password: String): Boolean {
        return !(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || password.length < 6)
    }
}
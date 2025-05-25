package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.auth.SignUpUseCase
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val navigator: Navigator,
    val signUpUseCase: SignUpUseCase
): ViewModel() {

    private val _state = MutableStateFlow(SignUpStateUi.DEFAULT)
    val state: StateFlow<SignUpStateUi>; get() = _state

    fun onSignUpClicked() {
        val state = state.value
        viewModelScope.launch {
            val signUp = signUpUseCase(
                email = state.email,
                password = state.password,
                isOrganization = state.isOrganization,
                userSurname = state.userSurname,
                userName = state.userName,
                userFatherName = state.userFatherName,
                orgPublicName = state.orgPublicName,
                orgGovName = state.orgGovName,
                orgType = state.orgType,
                orgCode = state.orgCode
            )
            if (signUp != null) navigator.navigateToMainScreen()
        }
    }

    fun onVolunteerClicked() {
        _state.update { it.copy(isOrganization = false) }
    }

    fun onOrganizationClicked() {
        _state.update { it.copy(isOrganization = true) }
    }

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onRepeatPasswordChanged(repeatPassword: String) {
        _state.update { it.copy(repeatPassword = repeatPassword) }
    }

    fun onUserSurnameChanged(userSurname: String) {
        _state.update { it.copy(userSurname = userSurname) }
    }

    fun onUserNameChanged(userName: String) {
        _state.update { it.copy(userName = userName) }
    }

    fun onUserFatherNameChanged(userFatherName: String) {
        _state.update { it.copy(userFatherName = userFatherName) }
    }

    fun onUserIsAdultChanged(userIsAdult: Boolean) {
        _state.update { it.copy(userIsAdult = userIsAdult) }
    }

    fun onOrgPublicNameChanged(orgPublicName: String) {
        _state.update { it.copy(orgPublicName = orgPublicName) }
    }

    fun onOrgGovNameChanged(orgGovName: String) {
        _state.update { it.copy(orgGovName = orgGovName) }
    }

    fun onOrgTypeChanged(orgType: String) {
        _state.update { it.copy(orgType = orgType) }
    }

    fun onOrgCodeChanged(orgCode: String) {
        _state.update { it.copy(orgCode = orgCode) }
    }
}
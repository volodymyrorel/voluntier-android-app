package uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.auth.LogOutUseCase
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigator: Navigator,
    private val logOutUseCase: LogOutUseCase,
    private val authHelper: AuthHelper
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileStateUi.DEFAULT)
    val state: StateFlow<ProfileStateUi>; get() = _state

    init {
        viewModelScope.launch {
            val result = authHelper.getCurrentUserData()
            _state.update { it.copy(
                email = result?.data?.get("email") as String? ?: "",
                role = result?.data?.get("role") as String? ?: "",
                surname = result?.data?.get("userSurname") as String? ?: "",
                name = result?.data?.get("userName") as String? ?: "",
                fatherName = result?.data?.get("userFatherName") as String? ?: "",
                publicName = result?.data?.get("orgPublicName") as String? ?: "",
                govName = result?.data?.get("orgGovName") as String? ?: "",
                type = result?.data?.get("orgType") as String? ?: "",
                code = result?.data?.get("orgCode") as String? ?: ""
            ) }
        }
    }
    fun onLogOutClicked() {
        viewModelScope.launch {
            logOutUseCase()
//            navigator.popBackStack()
            navigator.navigateToLoginScreen()
        }
    }
}
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
import uzhnu.volodymyrorel.voluntier.domain.utils.ktx.toLocalDateTime
import java.time.format.DateTimeFormatter
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
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
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
                code = result?.data?.get("orgCode") as String? ?: "",
                createdAt = ((result?.data?.get("createdAt") as Long?) ?: 0L).toLocalDateTime().format(formatter)
            ) }
        }
    }
    fun onLogOutClicked() {
        viewModelScope.launch {
            logOutUseCase()
            navigator.navigateToLoginScreen()
        }
    }
}
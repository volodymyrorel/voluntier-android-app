package uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.auth.LogOutUseCase
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val navigator: Navigator,
    val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileStateUi.DEFAULT)
    val state: StateFlow<ProfileStateUi>; get() = _state

    fun onLogOutClicked() {
        viewModelScope.launch {
            logOutUseCase()
//            navigator.popBackStack()
            navigator.navigateToLoginScreen()
        }
    }
}
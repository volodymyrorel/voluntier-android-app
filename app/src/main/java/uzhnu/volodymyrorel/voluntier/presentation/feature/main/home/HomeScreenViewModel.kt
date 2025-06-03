package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authHelper: AuthHelper,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeStateUi.DEFAULT)
    val state: StateFlow<HomeStateUi>; get() = _state

    init {
        viewModelScope.launch {
            val userRole = authRepository.getCurrentUserRole()
            _state.update { it.copy(userRole = userRole) }
        }
    }

    fun onCreateNewRequestClicked() {
        navigator.navigateToCreateNewRequestScreen()
    }
}
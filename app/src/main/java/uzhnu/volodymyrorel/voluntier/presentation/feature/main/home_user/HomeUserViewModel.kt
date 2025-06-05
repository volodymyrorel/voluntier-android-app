package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.answer.GetHomeUserScreenDataUseCase
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class HomeUserViewModel @Inject constructor(
    private val getHomeUserScreenDataUseCase: GetHomeUserScreenDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUserStateUi.DEFAULT)
    val state: StateFlow<HomeUserStateUi>; get() = _state

    init {
        viewModelScope.launch {
            _state.value = getHomeUserScreenDataUseCase()
        }
    }
}
package uzhnu.volodymyrorel.voluntier.presentation.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainStateUi.DEFAULT)
    val state: StateFlow<MainStateUi>; get() = _state

    init {
        viewModelScope.launch {
            val role = authRepository.getCurrentUserRole()
            _state.update { it.copy(role = role) }
        }
    }
}
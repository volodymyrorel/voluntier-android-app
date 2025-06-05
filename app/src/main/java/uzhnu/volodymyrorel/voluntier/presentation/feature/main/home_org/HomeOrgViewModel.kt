package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_org

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class HomeOrgViewModel @Inject constructor(
    private val demandRepository: DemandRepository,
    private val authHelper: AuthHelper,
    private val navigator: Navigator
) : ViewModel() {

    private val _state = MutableStateFlow(HomeOrgStateUi.DEFAULT)
    val state: StateFlow<HomeOrgStateUi>; get() = _state

    init {
        viewModelScope.launch {
            val demands = demandRepository.getOrganizationDemands(authHelper.user.id)
            _state.update { it.copy(demands = demands) }
        }
    }

    fun onCreateNewDemandClicked() {
        navigator.navigateToCreateNewRequestScreen()
    }

    fun onDemandClicked(demandId: String) {
        navigator.navigateToCreateNewAnswerScreen(demandId)
    }
}
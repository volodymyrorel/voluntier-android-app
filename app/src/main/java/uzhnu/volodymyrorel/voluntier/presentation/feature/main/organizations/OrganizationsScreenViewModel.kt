package uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.organization.OrganizationRepository
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.mapper.mapToUi
import javax.inject.Inject

@HiltViewModel
class OrganizationsScreenViewModel @Inject constructor(
    val organizationRepository: OrganizationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OrganizationsStateUi.DEFAULT)
    val state: StateFlow<OrganizationsStateUi>; get() = _state

    init {
        viewModelScope.launch {
            val organizations = organizationRepository.getOrganizations()
            _state.update { it.copy(orgs = organizations.map { it.mapToUi() }) }
        }
    }

    fun onOrganizationClicked(orgId: String) {

    }
}
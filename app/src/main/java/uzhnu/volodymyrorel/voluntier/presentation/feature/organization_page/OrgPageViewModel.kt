package uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import uzhnu.volodymyrorel.voluntier.domain.organization.OrganizationRepository
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.mapper.mapToUi
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class OrgPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val organizationRepository: OrganizationRepository,
    private val demandRepository: DemandRepository,
    private val authRepository: AuthRepository,
    private val navigator: Navigator
) : ViewModel() {

    private val _state = MutableStateFlow(OrgPageStateUi(uid = savedStateHandle.toRoute<OrgPageScreenConstants.Args>().uid))
    val state: StateFlow<OrgPageStateUi>; get() = _state

    init {
        viewModelScope.launch {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val orgDetails = organizationRepository.getOrganization(state.value.uid)
            val demandsDetails = demandRepository.getOrganizationDemands(state.value.uid)
            _state.update {
                it.copy(
                    email = orgDetails!!.email,
                    publicName = orgDetails.publicName,
                    govName = orgDetails.govName,
                    type = orgDetails.type,
                    code = orgDetails.code,
                    description = orgDetails.description,
                    isVerified = orgDetails.isVerified,
                    createdAt = orgDetails.createdAt.format(formatter),
                    updatedAt = orgDetails.updatedAt.format(formatter),
                    demands = demandsDetails.mapToUi(),
                    isUser = authRepository.getCurrentUserRole() == "user"
                )
            }
        }
    }

    fun onFundraisingClicked(id: String) {
        navigator.navigateToCreateNewAnswerScreen(id)
    }

    fun onMaterialClicked(id: String) {
        navigator.navigateToCreateNewAnswerScreen(id)
    }

    fun onVolunteersClicked(id: String) {
        navigator.navigateToCreateNewAnswerScreen(id)
    }
}
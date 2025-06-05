package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.answer.AnswerRepository
import uzhnu.volodymyrorel.voluntier.domain.answer.CreateFundAnswerUseCase
import uzhnu.volodymyrorel.voluntier.domain.answer.GetDemandAnswersDataUseCase
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CreateNewAnswerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val demandRepository: DemandRepository,
    private val answerRepository: AnswerRepository,
    private val authRepository: AuthRepository,
    private val authHelper: AuthHelper,
    private val navigator: Navigator,
    private val createFundAnswerUseCase: CreateFundAnswerUseCase,
    private val getDemandAnswersDataUseCase: GetDemandAnswersDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CreateNewAnswerStateUi(demandId = savedStateHandle.toRoute<CreateNewAnswerConstants.Args>().uid))
    val state: StateFlow<CreateNewAnswerStateUi>; get() = _state

    init {
        viewModelScope.launch {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            val demandDetails = demandRepository.getDemandById(state.value.demandId)
            if (demandDetails == null) navigator.popBackStack()
            else { _state.update { it.copy(
                demandOrgId = demandDetails.ownerId,
                demandTitle = demandDetails.title,
                demandDescription = demandDetails.description,
                currentSum = demandDetails.currentSum,
                targetSum = demandDetails.targetSum,
                type = demandDetails.type,
                userAnswers = answerRepository.getAnswersFromUserOnDemand(
                    userId = authHelper.user.id,
                    demandId = savedStateHandle.toRoute<CreateNewAnswerConstants.Args>().uid),
                demandAnswers = getDemandAnswersDataUseCase(savedStateHandle.toRoute<CreateNewAnswerConstants.Args>().uid),
                createdAt = demandDetails.createdAt.format(formatter),
                updatedAt = demandDetails.updatedAt.format(formatter),
                isOrg = authRepository.getCurrentUserRole() == "organization"
            ) } }
        }
    }

    fun onAnswerSumChanged(value: String) {
        _state.update { it.copy(answerSum = value) }
    }

    fun onAnswerDescriptionChanged(value: String) {
        _state.update { it.copy(answerDescription = value) }
    }

    fun onSendClicked() {
        var result: Unit? = null
        viewModelScope.launch {
            when (state.value.type) {
                Demand.TYPE_FUNDRAISING ->
                    result = createFundAnswerUseCase(
                        demandId = state.value.demandId,
                        sum = state.value.answerSum.toDouble(),
                        description = state.value.answerDescription.ifBlank { null }
                    )
                Demand.TYPE_VOLUNTEERS ->
                    result = answerRepository.createVolunteersAnswer(
                        demandId = state.value.demandId,
                        description = state.value.answerDescription
                    )
                Demand.TYPE_MATERIAL ->
                    result = answerRepository.createMaterialAnswer(
                        demandId = state.value.demandId,
                        description = state.value.answerDescription
                    )
            }
            navigator.popBackStack()
            navigator.popBackStack()
        }
    }

    fun isCurrentUserOwner(): Boolean {
        return authHelper.user.id == state.value.demandOrgId
    }
}
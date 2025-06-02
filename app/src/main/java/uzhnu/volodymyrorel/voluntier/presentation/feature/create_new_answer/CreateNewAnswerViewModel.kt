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
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CreateNewAnswerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val demandRepository: DemandRepository,
    private val answerRepository: AnswerRepository,
    private val authHelper: AuthHelper,
    private val navigator: Navigator
) : ViewModel() {

    private val _state = MutableStateFlow(CreateNewAnswerStateUi(demandId = savedStateHandle.toRoute<CreateNewAnswerConstants.Args>().uid))
    val state: StateFlow<CreateNewAnswerStateUi>; get() = _state

    init {
        viewModelScope.launch {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val demandDetails = demandRepository.getDemandById(state.value.demandId)
            if (demandDetails == null) navigator.popBackStack()
            else { _state.update { it.copy(
                demandTitle = demandDetails.title,
                demandDescription = demandDetails.description,
                currentSum = demandDetails.currentSum,
                targetSum = demandDetails.targetSum,
                type = demandDetails.type,
                userAnswers = answerRepository.getAnswersFromUserOnDemand(
                    userId = authHelper.user.id,
                    demandId = savedStateHandle.toRoute<CreateNewAnswerConstants.Args>().uid),
                createdAt = demandDetails.createdAt.format(formatter),
                updatedAt = demandDetails.updatedAt.format(formatter)
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

    }
}
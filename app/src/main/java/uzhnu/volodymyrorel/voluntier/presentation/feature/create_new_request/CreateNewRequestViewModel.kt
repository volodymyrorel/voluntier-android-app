package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.answer.CreateFundAnswerUseCase
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class CreateNewRequestViewModel @Inject constructor(
    private val navigator: Navigator,
    private val demandRepository: DemandRepository,
    private val createFundAnswerUseCase: CreateFundAnswerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CreateNewRequestStateUi.DEFAULT)
    val state: StateFlow<CreateNewRequestStateUi>; get() = _state

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> get() = _event.asSharedFlow()

    fun onCreateClicked() {

        viewModelScope.launch {
            // add when case for diff types
            val state = state.value
            var result: Unit? = null
            when (state.type) {
                Demand.TYPE_FUNDRAISING ->
                    if (state.sum.toDouble() > 0 && state.description.length > 5 && state.title.length > 5) {
                        result = demandRepository.createNewFundraisingDemand(
                            title = state.title,
                            description = state.description.ifBlank { null },
                            sum = state.sum.toDouble()
                        )
                    }
                Demand.TYPE_VOLUNTEERS ->
                    if (state.description.length > 5 && state.title.length > 5) {
                        result = demandRepository.createNewVolunteersDemand(
                            title = state.title,
                            description = state.description
                        )
                    }
                Demand.TYPE_MATERIAL ->
                    if (state.description.length > 5 && state.title.length > 5) {
                        result = demandRepository.createNewMaterialDemand(
                            title = state.title,
                            description = state.description
                        )
                    }
            }
            if (result != null) {
                navigator.popBackStack()
            } else _event.emit(Event.ShowToast("Someth went wrong"))
        }
    }

    fun onFundraisingClicked() {
        _state.update { it.copy(type = "fundraising") }
    }

    fun onVolunteersClicked() {
        _state.update { it.copy(type = "volunteers") }
    }

    fun onMaterialClicked() {
        _state.update { it.copy(type = "material") }
    }

    fun onTitleChanged(value: String) {
        _state.update { it.copy(title = value) }
    }

    fun onDescriptionChanged(value: String) {
        _state.update { it.copy(description = value) }
    }

    fun onSumChanged(value: String) {
        _state.update { it.copy(sum = value) }
    }
}
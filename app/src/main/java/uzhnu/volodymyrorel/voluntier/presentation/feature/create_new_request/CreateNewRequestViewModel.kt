package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class CreateNewRequestViewModel @Inject constructor(
    private val navigator: Navigator,
    private val demandRepository: DemandRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CreateNewRequestStateUi.DEFAULT)
    val state: StateFlow<CreateNewRequestStateUi>; get() = _state

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> get() = _event.asSharedFlow()

    fun onCreateClicked() {

        viewModelScope.launch {
            // add when case for diff types
            val state = state.value
            val result = demandRepository.createNewDemand(
                type = Demand.TYPE_FUNDRAISING,
                title = "test fr",
                description = null,
                sum = "100.25".toDouble()
            )
            if (result != null) {
                navigator.popBackStack()
            } else _event.emit(Event.ShowToast("Someth went wrong"))
        }
    }
}
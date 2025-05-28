package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CreateNewAnswerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CreateNewAnswerStateUi(demandId = savedStateHandle.toRoute<CreateNewAnswerConstants.Args>().uid))
    val state: StateFlow<CreateNewAnswerStateUi>; get() = _state

    init {

    }
}
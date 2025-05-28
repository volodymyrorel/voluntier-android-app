package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CreateNewAnswerRoute(
    viewModel: CreateNewAnswerViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
}
package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.presentation.core.components.ObserveSingleEvent

@Composable
fun CreateNewRequestRoute(
    viewModel: CreateNewRequestViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var toast: Toast? = remember { null }
    ObserveSingleEvent(viewModel.event) { event ->
        when (event) {
            is Event.ShowToast -> {
                toast?.cancel()
                toast = Toast.makeText(context, event.message, Toast.LENGTH_LONG)
                toast?.show()
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateNewRequestScreen(
        state = state,
        onCreateClicked = viewModel::onCreateClicked
    )
}

@Composable
fun CreateNewRequestScreen(
    state: CreateNewRequestStateUi,
    onCreateClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("This is CreateNewRequestScreen")
        Button(
            onClick = onCreateClicked
        ) {
            Text("Create")
        }
    }
}
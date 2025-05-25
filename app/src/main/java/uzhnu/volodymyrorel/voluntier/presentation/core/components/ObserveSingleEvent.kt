package uzhnu.volodymyrorel.voluntier.presentation.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun <T> ObserveSingleEvent(eventFlow: SharedFlow<T>, onEvent: (T) -> Unit) {
    LaunchedEffect(Unit) {
        eventFlow.collect(onEvent)
    }
}
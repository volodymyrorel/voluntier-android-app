@file:OptIn(ExperimentalMaterial3Api::class)

package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer.components.CreateNewAnswerScreenHeader

@Composable
fun CreateNewAnswerRoute(
    viewModel: CreateNewAnswerViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateNewAnswerScreen(
        state = state,
        onAnswerSumChanged = viewModel::onAnswerSumChanged,
        onAnswerDescriptionChanged = viewModel::onAnswerDescriptionChanged,
        onSendClicked = viewModel::onSendClicked
    )
}

@Composable
fun CreateNewAnswerScreen(
    state: CreateNewAnswerStateUi,
    onAnswerSumChanged: (String) -> Unit,
    onAnswerDescriptionChanged: (String) -> Unit,
    onSendClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
            title = {
                Text("Demand")
            }
        ) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            CreateNewAnswerScreenHeader(state = state)
            HorizontalDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.type == "fundraising") {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            Text("Your Sum")
                        },
                        value = state.answerSum,
                        onValueChange = { onAnswerSumChanged(it) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                if (state.type == "volunteers" && state.userAnswers.isNotEmpty()) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "You have already answered that volunteers demand.",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Red)
                    )
                } else {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            Text("Description")
                        },
                        value = state.answerDescription,
                        onValueChange = { onAnswerDescriptionChanged(it) }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !(state.type == "volunteers" && state.userAnswers.isNotEmpty()),
                        onClick = onSendClicked
                    ) {
                        Text(text = "Send Answer", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateNewAnswerScreenPreview() {
    AppTheme {
        CreateNewAnswerScreen(
            CreateNewAnswerStateUi(
                demandId = "123",
                demandTitle = "Збір на РУСОРІЗ",
                demandDescription = "Допоможіть закрити суму\n" +
                        "Там ще 2 з чимось тисяч лишилось зібрати",
                currentSum = 250.0,
                targetSum = 25000.0,
                type = "fundraising",
                createdAt = "01/01/1980",
                updatedAt = "01/01/2000"
            ),
            onAnswerSumChanged = {},
            onAnswerDescriptionChanged = {},
            onSendClicked = {}
        )
    }
}
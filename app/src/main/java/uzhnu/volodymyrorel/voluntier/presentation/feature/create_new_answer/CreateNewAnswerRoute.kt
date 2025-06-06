@file:OptIn(ExperimentalMaterial3Api::class)

package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.R
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer.components.CreateNewAnswerScreenHeader
import java.time.format.DateTimeFormatter

@Composable
fun CreateNewAnswerRoute(
    viewModel: CreateNewAnswerViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateNewAnswerScreen(
        state = state,
        onAnswerSumChanged = viewModel::onAnswerSumChanged,
        onAnswerDescriptionChanged = viewModel::onAnswerDescriptionChanged,
        onSendClicked = viewModel::onSendClicked,
        isCurrentUserOwner = viewModel::isCurrentUserOwner
    )
}

@Composable
fun CreateNewAnswerScreen(
    state: CreateNewAnswerStateUi,
    onAnswerSumChanged: (String) -> Unit,
    onAnswerDescriptionChanged: (String) -> Unit,
    onSendClicked: () -> Unit,
    isCurrentUserOwner: () -> Boolean
) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.request),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                CreateNewAnswerScreenHeader(state = state)
                HorizontalDivider()
            }
            if (isCurrentUserOwner()) {
                items(state.demandAnswers) { answer ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            )
                    ) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${answer.userSurname} ${answer.userName} ${answer.userFatherName}",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = answer.userEmail,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        if (answer.answer.description != null) {
                            Text(
                                text = stringResource(R.string.description),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = answer.answer.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        if (state.type == Demand.TYPE_FUNDRAISING) {
                            Text(
                                text = stringResource(R.string.sum),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = answer.answer.sum.toString(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Text(
                            text = stringResource(R.string.answered_at_column),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = answer.answer.createdAt.format(formatter),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider()
                    }

                }
            } else if (!state.isOrg) {
                item {
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
                                    Text(text = stringResource(R.string.sum))
                                },
                                value = state.answerSum,
                                onValueChange = { onAnswerSumChanged(it) },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Decimal
                                ),
                                singleLine = true
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if (state.type == "volunteers" && state.userAnswers.isNotEmpty()) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = stringResource(R.string.you_have_already_answered),
                                style = MaterialTheme.typography.titleMedium.copy(color = Color.Red)
                            )
                        } else {
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                label = {
                                    Text(text = stringResource(R.string.description))
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
                                Text(
                                    text = stringResource(R.string.send_answer),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
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
            onSendClicked = {},
            isCurrentUserOwner = { false }
        )
    }
}
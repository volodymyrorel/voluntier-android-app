@file:OptIn(ExperimentalMaterial3Api::class)

package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.presentation.core.components.ObserveSingleEvent
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.R

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
        onCreateClicked = viewModel::onCreateClicked,
        onFundraisingClicked = viewModel::onFundraisingClicked,
        onVolunteersClicked = viewModel::onVolunteersClicked,
        onMaterialClicked = viewModel::onMaterialClicked,
        onTitleChanged = viewModel::onTitleChanged,
        onDescriptionChanged = viewModel::onDescriptionChanged,
        onSumChanged = viewModel::onSumChanged
    )
}

@Composable
fun CreateNewRequestScreen(
    state: CreateNewRequestStateUi,
    onCreateClicked: () -> Unit,
    onFundraisingClicked: () -> Unit,
    onVolunteersClicked: () -> Unit,
    onMaterialClicked: () -> Unit,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onSumChanged: (String) -> Unit,
    ) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.create_new_request),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onFundraisingClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (state.type == "fundraising") MaterialTheme.colorScheme.secondary
                        else Color.Transparent
                )
            ) {
                Text(
                    text = stringResource(R.string.fundraising),
                    color =
                        if (state.type == "fundraising") Color.Unspecified
                        else Color.Black
                )
            }
            Button(
                onClick = onVolunteersClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (state.type == "volunteers") MaterialTheme.colorScheme.secondary
                        else Color.Transparent
                )
            ) {
                Text(
                    text = stringResource(R.string.volunteers),
                    color =
                        if (state.type == "volunteers") Color.Unspecified
                        else Color.Black
                )
            }
            Button(
                onClick = onMaterialClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (state.type == "material") MaterialTheme.colorScheme.secondary
                        else Color.Transparent
                )
            ) {
                Text(
                    text = stringResource(R.string.material),
                    color =
                        if (state.type == "material") Color.Unspecified
                        else Color.Black
                )
            }
            HorizontalDivider()
            if (state.type != null) {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                    ,
                    label = {
                        Text(text = stringResource(R.string.title))
                    },
                    value = state.title,
                    onValueChange = { onTitleChanged(it) }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(text = stringResource(R.string.description))
                    },
                    value = state.description,
                    onValueChange = { onDescriptionChanged(it) }
                )
                if (state.type == Demand.TYPE_FUNDRAISING) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        label = {
                            Text(text = stringResource(R.string.target_sum))
                        },
                        value = state.sum,
                        onValueChange = { onSumChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        ),
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onCreateClicked
                ) {
                    Text(text = stringResource(R.string.create_request))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateNewRequestScreenPreview() {
    AppTheme {
        CreateNewRequestScreen(
            state = CreateNewRequestStateUi.DEFAULT.copy(type = Demand.TYPE_FUNDRAISING),
            onCreateClicked = {},
            onFundraisingClicked = {},
            onVolunteersClicked = {},
            onMaterialClicked = {},
            onTitleChanged = {},
            onDescriptionChanged = {},
            onSumChanged = {}
        )
    }
}
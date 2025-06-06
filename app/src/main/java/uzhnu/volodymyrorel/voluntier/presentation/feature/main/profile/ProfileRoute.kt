@file:OptIn(ExperimentalMaterial3Api::class)

package uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.R

const val profileRoute = "profileRoute"

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        state = state,
        onLogOutClicked = viewModel::onLogOutClicked
    )
}

@Composable
fun ProfileScreen(
    state: ProfileStateUi,
    onLogOutClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.profile),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (state.role == "user") {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .border(
                                        width = 2.dp,
                                        color = MaterialTheme.colorScheme.secondaryContainer,
                                        shape = CircleShape
                                    )
                            )
                            Icon(
                                modifier = Modifier.size(48.dp),
                                imageVector = Icons.Default.Person,
                                tint = MaterialTheme.colorScheme.secondary,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "${state.surname} ${state.name} ${state.fatherName}",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = state.email,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.joined_on_column),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.createdAt,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .border(
                                        width = 2.dp,
                                        color = MaterialTheme.colorScheme.secondaryContainer
                                    )
                            )
                            Icon(
                                modifier = Modifier.size(48.dp),
                                painter = painterResource(R.drawable.ic_group_60),
                                tint = MaterialTheme.colorScheme.secondary,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = state.publicName!!, style = MaterialTheme.typography.titleLarge)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = state.email, style = MaterialTheme.typography.labelMedium)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.type),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.type!!,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.government_name),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.govName!!,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.government_code),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.code!!,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.joined_on_column),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.createdAt,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1F))
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = onLogOutClicked
            ) {
                Text(text = stringResource(R.string.log_out))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            state = ProfileStateUi.DEFAULT,
            onLogOutClicked = {}
        )
    }
}
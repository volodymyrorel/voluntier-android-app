@file:OptIn(ExperimentalMaterial3Api::class)

package uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.components.OrganizationItem

const val organizationsRoute = "organizationsRoute"

@Composable
fun OrganizationsRoute(
    viewModel: OrganizationsScreenViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    OrganizationsScreen(
        state = state
    )
}

@Composable
fun OrganizationsScreen(
    state: OrganizationsStateUi
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Organizations")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.orgs, key = { item ->
                item.uid
            }) { item ->
                OrganizationItem(
                    organization = item,
                    onOrganizationClicked = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrganizationsScreenPreview() {
    AppTheme {
        OrganizationsScreen(
            state = OrganizationsStateUi.DEFAULT
        )
    }
}
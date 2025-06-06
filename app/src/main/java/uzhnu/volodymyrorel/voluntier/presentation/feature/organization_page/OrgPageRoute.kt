@file:OptIn(ExperimentalMaterial3Api::class)

package uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.components.OrganizationItem
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.OrgPageStateUi.DemandUi
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.components.FundraisingItem
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.components.MaterialItem
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.components.OrgPageHeader
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.components.VolunteersItem
import kotlin.String

@Composable
fun OrgPageRoute(
    viewModel: OrgPageViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    OrgPageScreen(
        state = state,
        onFundraisingClicked = viewModel::onFundraisingClicked,
        onMaterialClicked = viewModel::onMaterialClicked,
        onVolunteersClicked = viewModel::onVolunteersClicked
    )
}

@Composable
fun OrgPageScreen(
    state: OrgPageStateUi,
    onFundraisingClicked: (String) -> Unit,
    onMaterialClicked: (String) -> Unit,
    onVolunteersClicked: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.organization),
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
                .background(color = Color.LightGray)
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.height(320.dp),
                        painter = painterResource(R.drawable.ic_group_320),
                        contentDescription = null
                    )
                }
                OrgPageHeader(state = state)
                HorizontalDivider()
            }

            items(state.demands, key = { item ->
                item.id
            }) { item ->
                if (item.type == Demand.TYPE_FUNDRAISING)
                    FundraisingItem(
                        state = item,
                        onClick = onFundraisingClicked
                    )
                if (item.type == Demand.TYPE_MATERIAL)
                    MaterialItem(
                        state = item,
                        onClick = onMaterialClicked
                    )
                if (item.type == Demand.TYPE_VOLUNTEERS)
                    VolunteersItem(
                        state = item,
                        onClick = onVolunteersClicked
                    )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrgPageScreenPreview() {
    AppTheme {
        OrgPageScreen(
            state = OrgPageStateUi(
                uid = "",
                email = "",
                publicName = "",
                govName = "",
                type = "",
                code = "",
                description = null,
                isVerified = false,
                createdAt = "",
                updatedAt = "",
                demands = emptyList(),
                isUser = false
            ),
            onFundraisingClicked = {},
            onMaterialClicked = {},
            onVolunteersClicked = {})
    }
}
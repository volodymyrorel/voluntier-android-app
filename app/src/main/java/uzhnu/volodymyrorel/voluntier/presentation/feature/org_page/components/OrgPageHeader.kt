package uzhnu.volodymyrorel.voluntier.presentation.feature.org_page.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.presentation.feature.org_page.OrgPageStateUi
import uzhnu.volodymyrorel.voluntier.R

@Composable
fun OrgPageHeader(
    state: OrgPageStateUi
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = state.publicName, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold))
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = stringResource(R.string.public_name), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.publicName, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.government_name), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.govName, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.organization_type), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.type, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.email), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.email, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.government_code), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.code, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.joined_on_column), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.createdAt, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        if (state.description != null && state.description.isNotBlank()) {
            Text(text = stringResource(R.string.joined_on_column), style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = state.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrgPageHeaderPreview() {
    AppTheme {
        OrgPageHeader(
            state = OrgPageStateUi(
                uid = "",
                email = "test1@text.com",
                publicName = "SOM Zakarpattia",
                govName = "ГО \"Community on united youth\"",
                type = "NGO",
                code = "12345678",
                description = "Zakarpattia youth organization. You are the driving force, because it all starts with you and your idea.\"\n" +
                        "+ \"You can gather as many people around you as possible who will support you and then together you can create cool things",
                isVerified = false,
                createdAt = "12/04/2024",
                updatedAt = "12/04/2025",
                demands = emptyList(),
                isUser = true
            )
        )
    }
}
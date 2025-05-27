package uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page.OrgPageStateUi

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

        Text(text = "Public Name", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.publicName, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Government Name", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.govName, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Organization Type", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.type, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Email", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.email, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Registration Code", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.code, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Joined on", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.createdAt, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        if (state.description != null && state.description.isNotBlank()) {
            Text(text = "Description", style = MaterialTheme.typography.titleMedium)
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
                publicName = "СОМ Закарпаття",
                govName = "ГО \"Спільнота об'єднаної молоді Закарпаття\"",
                type = "Громадська організація",
                code = "12345678",
                description = "Zakarpattia youth organization. Ти - це рушійна сила, бо все починається з тебе і твоєї ідеї."
                        + "Навколо себе можливо зібрати багато прикольних людей, які підтримають тебе і тоді ви разом зможете створювати круті речі",
                isVerified = false,
                createdAt = "12/04/2024",
                updatedAt = "12/04/2025",
                demands = emptyList(),
                isUser = true
            )
        )
    }
}
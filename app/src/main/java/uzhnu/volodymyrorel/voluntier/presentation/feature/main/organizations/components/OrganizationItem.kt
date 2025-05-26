package uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uzhnu.volodymyrorel.voluntier.R
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations.OrganizationsStateUi
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter

@Composable
fun OrganizationItem(
    organization: OrganizationsStateUi.OrgStateUi,
    onOrganizationClicked: (String) -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onOrganizationClicked(organization.uid)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .height(60.dp),
                painter = painterResource(R.drawable.ic_group_60),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1F)
            ) {
                Text(
                    text = organization.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (organization.description != null) {
                    Text(
                        text = organization.description,
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Text(
                    text = "Joined on: " + formatter.format(organization.createdAt),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun OrganizationItemPreview() {
    AppTheme {
        OrganizationItem(
            OrganizationsStateUi.OrgStateUi(
                uid = "",
                name = "SOM Zakarpattia",
                description = "Zakarpattia youth organization. Ти - це рушійна сила, бо все починається з тебе і твоєї ідеї."
                        + "Навколо себе можливо зібрати багато прикольних людей, які підтримають тебе і тоді ви разом зможете створювати круті речі",
                createdAt = LocalDateTime.of(
                    2024,
                    Month.APRIL,
                    12,
                    12,
                    0,
                    0)
            ),
            onOrganizationClicked = {}
        )
    }
}
